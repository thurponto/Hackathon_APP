import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static const String baseUrl = 'http://localhost:8080'; // Quando for rodar na API real, ajuste o IP se necessário

  // GET - Buscar lista de alunos
  static Future<List<Map<String, dynamic>>> getAlunos() async {
    try {
      final response = await http.get(
        Uri.parse('$baseUrl/api/alunos'),
        headers: {'Content-Type': 'application/json'},
      );

      if (response.statusCode == 200) {
        final List<dynamic> data = json.decode(response.body);
        return data.cast<Map<String, dynamic>>();
      } else {  
        print('Erro ao buscar alunos: ${response.statusCode} - ${response.body}');
        return [];
      }
    } catch (e) {
      print('Exceção ao buscar alunos: $e');
      return [];
    }
  }



  // POST - Enviar respostas do aluno
  static Future<bool> enviarRespostas(int alunoId, List<String?> respostas) async {
    try {
      final response = await http.post(
        Uri.parse('$baseUrl/api/respostas'),
        headers: {'Content-Type': 'application/json'},
        body: json.encode({
          'alunoId': alunoId,
          'respostas': respostas,
        }),
      );

      print('Status: ${response.statusCode}');
      print('Body: ${response.body}');

      return response.statusCode == 200;
    } catch (e) {
      print('Erro ao enviar respostas: $e');
      return false;
    }
  }

}







