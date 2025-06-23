import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static const String baseUrl = 'http://localhost:8080'; // Quando for rodar na API real, ajuste o IP se necessário

  // GET - Buscar lista de alunos
  static Future<List<Map<String, dynamic>>> getAlunos() async {
    try {
      // MOCK enquanto a API real não está pronta
      await Future.delayed(Duration(seconds: 1));
      return [
        {'id': 1, 'nome': 'João Silva'},
        {'id': 2, 'nome': 'Maria Souza'},
        {'id': 3, 'nome': 'Pedro Lima'},
      ];

      /*
      // DESCOMENTE quando a API estiver pronta
      final response = await http.get(Uri.parse('$baseUrl/api/alunos'));
      if (response.statusCode == 200) {
        final List data = json.decode(response.body);
        return data.cast<Map<String, dynamic>>();
      } else {
        throw Exception('Falha ao carregar alunos');
      }
      */
    } catch (e) {
      throw Exception('Erro ao buscar alunos: $e');
    }
  }

  // POST - Enviar respostas do aluno
  static Future<bool> enviarRespostas(int alunoId, List<String?> respostas) async {
    try {
      // Aqui ajustamos: onde estiver vazio, transforma em null
      List<dynamic> respostasTratadas = respostas.map((r) => r == '' ? null : r).toList();

      print('======== ENVIO DE RESPOSTAS ========');
      print('Aluno ID: $alunoId');
      print('Respostas: $respostasTratadas');
      print('====================================');

      // MOCK - Enquanto a API não estiver pronta
      await Future.delayed(Duration(seconds: 1));
      return true;

      /*
      // DESCOMENTE quando for enviar de verdade para a API
      final response = await http.post(
        Uri.parse('$baseUrl/api/respostas'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode({
          'alunoId': alunoId,
          'respostas': respostasTratadas,
        }),
      );
      print('Status Code: ${response.statusCode}');
      print('Resposta da API: ${response.body}');
      return response.statusCode == 200;
      */
    } catch (e) {
      print('Erro ao enviar respostas: $e');
      return false;
    }
  }
}
