import 'package:flutter/material.dart';
import 'SelecionarQuantidadeScreen.dart';

class SelecionarRaScreen extends StatefulWidget {
  @override
  _SelecionarRaScreenState createState() => _SelecionarRaScreenState();
}

class _SelecionarRaScreenState extends State<SelecionarRaScreen> {
  final TextEditingController _quantidadeController = TextEditingController(text: '5');
  late List<String> raRespostas;
  int totalDigitos = 5;

  @override
  void initState() {
    super.initState();
    raRespostas = List.filled(totalDigitos, '');
  }

  void atualizarQuantidade() {
    final qtd = int.tryParse(_quantidadeController.text);
    if (qtd == null || qtd <= 0) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Digite uma quantidade válida de dígitos.')),
      );
      return;
    }

    setState(() {
      totalDigitos = qtd;

      if (raRespostas.length < totalDigitos) {
        raRespostas = List.from(raRespostas)..addAll(List.filled(totalDigitos - raRespostas.length, ''));
      } else if (raRespostas.length > totalDigitos) {
        raRespostas = raRespostas.sublist(0, totalDigitos);
      }
    });
  }

  void selecionarNumero(int posicao, String numero) {
    setState(() {
      raRespostas[posicao] = numero;
    });
  }

  void confirmarRa() {
    final preenchido = raRespostas.every((r) => r.isNotEmpty);
    if (!preenchido) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Preencha todos os dígitos do RA antes de continuar.')),
      );
      return;
    }

    final raFinal = raRespostas.join('');
    // MOCK de aluno, substitua pela busca real na API se quiser
    final aluno = {'id': 999, 'nome': 'Aluno do RA $raFinal'};

    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => SelecionarQuantidadeScreen(aluno: aluno)),
    );
  }

  Widget buildLinhaRa(int index) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6.0),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('Dígito ${index + 1}:', style: TextStyle(fontWeight: FontWeight.bold)),
          SizedBox(width: 8),
          ...List.generate(10, (num) {
            final numero = num.toString();
            final selecionado = raRespostas[index] == numero;
            return Padding(
              padding: const EdgeInsets.symmetric(horizontal: 4),
              child: GestureDetector(
                onTap: () => selecionarNumero(index, numero),
                child: Container(
                  width: 32,
                  height: 32,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: selecionado ? Colors.blue : Colors.white,
                    border: Border.all(color: Colors.black),
                  ),
                  child: Center(
                    child: Text(
                      numero,
                      style: TextStyle(
                        color: selecionado ? Colors.white : Colors.black,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                ),
              ),
            );
          }),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Selecionar RA')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Text('Quantidade de dígitos do RA:'),
            SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(
                  width: 80,
                  child: TextField(
                    controller: _quantidadeController,
                    keyboardType: TextInputType.number,
                    textAlign: TextAlign.center,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: 'Ex: 5',
                    ),
                    onSubmitted: (_) => atualizarQuantidade(),
                    onEditingComplete: atualizarQuantidade,
                  ),
                ),
                SizedBox(width: 12),
                ElevatedButton(
                  onPressed: atualizarQuantidade,
                  child: Text('Atualizar'),
                ),
              ],
            ),
            SizedBox(height: 16),
            Expanded(
              child: ListView.builder(
                itemCount: totalDigitos,
                itemBuilder: (context, index) => buildLinhaRa(index),
              ),
            ),
            ElevatedButton.icon(
              onPressed: confirmarRa,
              icon: Icon(Icons.check),
              label: Text('Confirmar RA'),
            ),
          ],
        ),
      ),
    );
  }
}
