import 'package:flutter/material.dart';
import 'utils/app_theme.dart';
import 'screens/home_screen.dart';
import 'screens/aluno_list_screen.dart';
import 'screens/gabarito_screen.dart';
import 'screens/selecionar_ra_screen.dart';

void main() {
  runApp(CorrecaoGabaritoApp());
}

class CorrecaoGabaritoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Correção de Gabarito',
      theme: AppTheme.theme,
      initialRoute: '/home',
      routes: {
        '/home': (context) => HomeScreen(),
        '/alunos': (context) => AlunoListScreen(),
        '/gabarito': (context) => GabaritoScreen(),
        '/ra': (context) => SelecionarRaScreen(),
      },
    );
  }
}
