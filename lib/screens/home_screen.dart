import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Página Inicial')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Text('Bem-vindo! Aqui será a tela para enviar gabaritos.'),
            SizedBox(height: 16), // Espaço entre o texto e o botão
            ElevatedButton(
              onPressed: () {
                Navigator.pushNamed(context, '/ra');
              },
              child: Text('Preencher Respostas de Aluno'),
            ),
          ],
        ),
      ),
    );
  }
}
