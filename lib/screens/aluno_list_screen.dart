import 'package:flutter/material.dart';
import '../services/api_service.dart';
import 'SelecionarQuantidadeScreen.dart';

class AlunoListScreen extends StatefulWidget {
  @override
  _AlunoListScreenState createState() => _AlunoListScreenState();
}

class _AlunoListScreenState extends State<AlunoListScreen> {
  late Future<List<Map<String, dynamic>>> alunosFuture;

  @override
  void initState() {
    super.initState();
    alunosFuture = ApiService.getAlunos();
  }

  void selecionarAluno(Map<String, dynamic> aluno) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => SelecionarQuantidadeScreen(aluno: aluno),
      ),
    );
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Selecionar Aluno')),
      body: FutureBuilder<List<Map<String, dynamic>>>(
        future: alunosFuture,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Erro ao carregar alunos.'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return Center(child: Text('Nenhum aluno encontrado.'));
          } else {
            final alunos = snapshot.data!;
            return ListView.builder(
              itemCount: alunos.length,
              itemBuilder: (context, index) {
                final aluno = alunos[index];
                return ListTile(
                  title: Text(aluno['nome']),
                  onTap: () => selecionarAluno(aluno),
                );
              },
            );
          }
        },
      ),
    );
  }
}
