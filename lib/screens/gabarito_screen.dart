import 'package:flutter/material.dart';
import '../services/api_service.dart';

class GabaritoScreen extends StatefulWidget {
  @override
  _GabaritoScreenState createState() => _GabaritoScreenState();
}

class _GabaritoScreenState extends State<GabaritoScreen> {
   // Exemplo com 10 questões
  final List<String> opcoes = ['A', 'B', 'C', 'D', 'E'];
  late List<String> respostas;
  late Map<String, dynamic> aluno;

  late int totalQuestoes;

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    final args = ModalRoute.of(context)!.settings.arguments as Map<String, dynamic>;
    aluno = args['aluno'];
    totalQuestoes = args['quantidade'];
    respostas = List.filled(totalQuestoes, '');
  }


  void selecionarResposta(int questao, String opcao) {
    setState(() {
      respostas[questao] = opcao;
    });
  }


  void enviarRespostas() async {
    final totalRespondidas = respostas.where((r) => r.isNotEmpty).length;

    if (totalRespondidas == 0) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Você precisa responder pelo menos uma questão antes de enviar.')),
      );
      return;
    }

    final naoRespondidas = respostas.where((r) => r.isEmpty).length;

    if (naoRespondidas > 0) {
      bool confirmar = await showDialog<bool>(
        context: context,
        builder: (context) => AlertDialog(
          title: Text('Questões em branco'),
          content: Text('Você deixou $naoRespondidas questão(ões) sem responder. Tem certeza que deseja enviar?'),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context, false),
              child: Text('Cancelar'),
            ),
            TextButton(
              onPressed: () => Navigator.pop(context, true),
              child: Text('Enviar assim mesmo'),
            ),
          ],
        ),
      ) ?? false;

      if (!confirmar) return;
    }

    List<String?> respostasAjustadas = respostas.map((r) => r.isEmpty ? null : r).toList();

    bool sucesso = await ApiService.enviarRespostas(aluno['id'], respostasAjustadas);

    if (sucesso) {
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text('Sucesso'),
          content: Text('Respostas enviadas com sucesso!'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.popUntil(context, ModalRoute.withName('/home'));
              },
              child: Text('OK'),
            ),
          ],
        ),
      );
    } else {
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text('Erro'),
          content: Text('Falha ao enviar respostas.'),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: Text('OK'),
            ),
          ],
        ),
      );
    }
  }




  Widget buildLinhaQuestao(int index) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center, // Centraliza toda a linha
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            '${index + 1}',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          SizedBox(width: 12), // Pequeno espaço entre número e bolinhas
          ...opcoes.map((opcao) {
            bool selecionada = respostas[index] == opcao;
            return Padding(
              padding: const EdgeInsets.symmetric(horizontal: 6),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  Text(
                    opcao,
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  GestureDetector(
                    onTap: () => selecionarResposta(index, opcao),
                    child: Container(
                      margin: EdgeInsets.only(top: 4),
                      width: 40,
                      height: 40,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: selecionada ? Colors.blue : Colors.white,
                        border: Border.all(color: Colors.black),
                      ),
                      child: Center(
                        child: Text(
                          selecionada ? opcao : '',
                          style: TextStyle(
                            color: selecionada ? Colors.white : Colors.black,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            );
          }).toList(),
        ],
      ),
    );
  }







  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Gabarito - ${aluno['nome']}')),
      body: ListView.builder(
        itemCount: totalQuestoes,
        itemBuilder: (context, index) => buildLinhaQuestao(index),
      ),

      floatingActionButton: FloatingActionButton(
        onPressed: enviarRespostas,
        child: Icon(Icons.send),
      ),
    );
  }
}
