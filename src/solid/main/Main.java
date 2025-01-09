package solid.main;

import solid.dip.bad.Computador;
import solid.dip.good.ComputadorDIP;
import solid.dip.good.TecladoDIP;
import solid.isp.bad.Atendente;
import solid.isp.bad.Desenvolvedor;
import solid.isp.good.AtendenteImpl;
import solid.isp.good.DesenvolvedorImpl;
import solid.lsp.bad.Quadrado;
import solid.lsp.bad.Retangulo;
import solid.lsp.good.Forma;
import solid.lsp.good.QuadradoForma;
import solid.lsp.good.RetanguloForma;
import solid.ocp.bad.Calculadora;
import solid.ocp.good.Calculator;
import solid.ocp.good.MultiplicatioOperation;
import solid.ocp.good.SubtractionOperation;
import solid.ocp.good.SumOperation;
import solid.srp.bad.RelatorioFinanceiro;
import solid.srp.good.Email;
import solid.srp.good.RelatorioFinanceiroSRP;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Exercícios sobre SOLID ===");


        System.out.println("\n--> SRP - Simple Responsability Principle");

        System.out.println("- Bad");
        RelatorioFinanceiro rf = new RelatorioFinanceiro();
        rf.gerarRelatorio();
        rf.enviarPorEmail();

        System.out.println("- Good");
        RelatorioFinanceiroSRP rfg = new RelatorioFinanceiroSRP();
        rfg.gerarRelatorio();
        Email email = new Email();
        email.enviarPorEmail();


        System.out.println("\n--> OCP - Open/Closed Principle");

        System.out.println("- Bad");
        Calculadora calculadora = new Calculadora();
        int a = 5;
        int b = 4;
        System.out.println(a + " + " + b +" = " + calculadora.calcular(5,4, "soma"));
        System.out.println(a + " - " + b +" = " + calculadora.calcular(5,4,"subtracao"));

        System.out.println("- Good");
        System.out.println(a + " + " + b +" = " + new Calculator().calc(a,b,new SumOperation()));
        System.out.println(a + " - " + b +" = " + new Calculator().calc(a,b,new SubtractionOperation()));
        System.out.println(a + " * " + b +" = " + new Calculator().calc(a,b,new MultiplicatioOperation()));


        System.out.println("\n--> LSP - Liskov Substitution Principle");

        System.out.println("- Bad");
        Retangulo retangulo = new Retangulo();
        retangulo.setLargura(10);
        retangulo.setAltura(5);
        System.out.println("Área do retângulo: " + retangulo.getArea());

        Quadrado quadrado = new Quadrado();
        quadrado.setAltura(5);
        quadrado.setLargura(5);
        System.out.println("Área do quadrado: " + quadrado.getArea());

        System.out.println("- Good");
        RetanguloForma retanguloForma = new RetanguloForma(5,10);
        getAreaForma(retanguloForma);
        QuadradoForma quadradoForma = new QuadradoForma(5);
        getAreaForma(quadradoForma);


        System.out.println("\n--> ISP - Interface Segregation Principle");

        System.out.println("- Bad");
        Atendente atendente = new Atendente();
        atendente.atenderCliente();
        atendente.programar();
        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.atenderCliente();
        desenvolvedor.programar();

        System.out.println("- Good");
        AtendenteImpl atendenteImpl = new AtendenteImpl();
        atendenteImpl.atenderCliente();
        DesenvolvedorImpl desenvolvedorImpl = new DesenvolvedorImpl();
        desenvolvedorImpl.programar();


        System.out.println("\n--> DIP - Dependency Inversion Principle");

        System.out.println("- Bad");
        Computador computador = new Computador();
        computador.escrever();

        System.out.println("- Good");
        ComputadorDIP computadorDIP = new ComputadorDIP(new TecladoDIP());
        computadorDIP.escrever();

    }

    public static void getAreaForma(Forma forma){
        System.out.println("Área " + forma.getClass() + ": " + forma.getArea());
    }
}