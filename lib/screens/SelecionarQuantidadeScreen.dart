import 'package:flutter/material.dart';

class SelecionarQuantidadeScreen extends StatefulWidget {
  final Map<String, dynamic> aluno;

  SelecionarQuantidadeScreen({required this.aluno});

  @override
  _SelecionarQuantidadeScreenState createState() => _SelecionarQuantidadeScreenState();
}

class _SelecionarQuantidadeScreenState extends State<SelecionarQuantidadeScreen> {
  final TextEditingController _controller = TextEditingController(text: '10'); // Valor padrão inicial

  void irParaGabarito() {
    final textoDigitado = _controller.text;
    final quantidadeDigitada = int.tryParse(textoDigitado);

    if (quantidadeDigitada == null || quantidadeDigitada <= 0) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Por favor, digite uma quantidade válida de questões.')),
      );
      return;
    }

    Navigator.pushNamed(
      context,
      '/gabarito',
      arguments: {
        'aluno': widget.aluno,
        'quantidade': quantidadeDigitada,
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Selecionar Quantidade de Questões')),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Text('Digite a quantidade de questões:'),
              SizedBox(height: 10),
              SizedBox(
                width: 150,  // <<< Aqui controla o tamanho da caixa de texto
                child: TextField(
                  controller: _controller,
                  keyboardType: TextInputType.number,
                  textAlign: TextAlign.center,
                  decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Ex: 10',
                  ),
                ),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: irParaGabarito,
                child: Text('Confirmar'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
