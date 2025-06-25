package com.gabarito.correcao.controller;

import com.gabarito.correcao.security.JwtFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtFilter jwtFilter;

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email,
                              @RequestParam String senha,
                              Model model,
                              HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha)
            );
            // Login bem-sucedido!
            System.out.println("Autenticado com sucesso: " + auth.getName());

        } catch (BadCredentialsException e) {
            System.out.println("Credenciais inválidas: email ou senha incorretos.");

        } catch (DisabledException e) {
            System.out.println("Conta desativada. Fale com o administrador.");

        } catch (LockedException e) {
            System.out.println("Conta bloqueada. Tente novamente mais tarde.");

        } catch (AccountExpiredException e) {
            System.out.println("Conta expirada. Acesse o suporte.");

        } catch (CredentialsExpiredException e) {
            System.out.println("Senha expirada. Atualize sua senha.");

        } catch (AuthenticationException e) {
            // Caso algum outro erro inesperado aconteça
            System.out.println("Erro de autenticação: " + e.getMessage());
        }



        String token = jwtFilter.generateToken(email);
            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/dashboard-professor";
        }
    }


