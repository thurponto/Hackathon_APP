import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'utils/app_theme.dart';
import 'screens/login_screen.dart';
import 'screens/home_screen.dart';
import 'screens/aluno_list_screen.dart';
import 'screens/gabarito_screen.dart';
import 'screens/selecionar_ra_screen.dart';


Future<bool> checkLogin() async {
  final prefs = await SharedPreferences.getInstance();
  return prefs.getBool('isLoggedIn') ?? false;
}


void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  bool isLoggedIn = await checkLogin();

  runApp(CorrecaoGabaritoApp(isLoggedIn: isLoggedIn));
}

class CorrecaoGabaritoApp extends StatelessWidget {
  final bool isLoggedIn;
  CorrecaoGabaritoApp({required this.isLoggedIn});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Correção de Gabarito',
      theme: AppTheme.theme,
      initialRoute: isLoggedIn ? '/home' : '/',
      routes: {
        '/': (context) => LoginScreen(),
        '/home': (context) => HomeScreen(),
        '/alunos': (context) => AlunoListScreen(),
        '/gabarito': (context) => GabaritoScreen(),
        '/ra': (context) => SelecionarRaScreen(),
      },
    );
  }
}

