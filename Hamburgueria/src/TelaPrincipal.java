import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import Model.Hamburgueria;
import Repository.HamburgueriaRepository;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame implements ActionListener {
    JFrame frame = new JFrame("Good Burger");
    JPanel painel = new JPanel();
    JLabel imagem = new JLabel();
    JLabel dadoscliente = new JLabel();
    JLabel nome = new JLabel();
    JTextArea nome1 = new JTextArea();
    JLabel cpf = new JLabel();
    JTextArea cpf1 = new JTextArea();
    JLabel sexo = new JLabel();
    JRadioButton feminino = new JRadioButton();
    JRadioButton masculino = new JRadioButton();
    ButtonGroup grupoSexo = new ButtonGroup();
    int generoCliente;
    JLabel formaPagamento = new JLabel();
    JRadioButton cartaocredito = new JRadioButton();
    JRadioButton dinheiro = new JRadioButton();
    JRadioButton pix = new JRadioButton();
    JComboBox comboPagamento = new JComboBox<>();
    JLabel dadositem = new JLabel();
    JLabel item = new JLabel();
    JTextArea item1 = new JTextArea();
    JLabel preco = new JLabel();
    JTextArea preco1 = new JTextArea();
    JLabel quantidade = new JLabel();
    JTextArea quantidade1 = new JTextArea();
    JButton finalizar = new JButton();
    JButton adicionar = new JButton();
    JButton consultar = new JButton();
    JButton remover = new JButton();
    JLabel tabelaPedidos = new JLabel();
    JLabel totalPedido = new JLabel();
    JTextArea totalPedido1 = new JTextArea();
    Hamburgueria hamburgueria = new Hamburgueria();
    HamburgueriaRepository repository = new HamburgueriaRepository();
    JTable tabela = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    int idHamburguer;
    float totalPedido2 = 0;

    public void telaInicial() {

        frame.setSize(765, 540);
        painel.setSize(765, 540);
        painel.setLayout(null);

        imagem.setBounds(0, 0, 985, 120);
        imagem.setIcon(new ImageIcon("img/ham.jpg"));

        dadoscliente.setText("Dados do Cliente");
        dadoscliente.setBounds(10, 140, 150, 15);

        nome.setText("Nome");
        nome.setBounds(10, 170, 50, 20);
        nome1.setBounds(60, 170, 160, 20);

        cpf.setText("CPF");
        cpf.setBounds(10, 170, 50, 80);
        cpf1.setBounds(60, 200, 160, 20);

        sexo.setText("Sexo");
        sexo.setBounds(240, 138, 50, 20);

        feminino.setText("Feminino");
        feminino.setBounds(238, 185, 76, 55);
        grupoSexo.add(feminino);

        masculino.setText("Masculino");
        masculino.setBounds(238, 154, 86, 50);
        grupoSexo.add(masculino);

        formaPagamento.setText("Forma de Pagamento");
        formaPagamento.setBounds(10, 240, 160, 20);

        comboPagamento.setBounds(145, 240, 200, 25);
        comboPagamento.addItem("Cartão de Crédito");
        comboPagamento.addItem("Dinheiro");
        comboPagamento.addItem("PIX");

        dadositem.setText("Dados do Item");
        dadositem.setBounds(10, 310, 150, 15);

        item.setText("Item");
        item.setBounds(10, 340, 50, 20);
        item1.setBounds(60, 340, 290, 20);

        preco.setText("Preço R$");
        preco.setBounds(10, 370, 90, 20);
        preco1.setBounds(70, 370, 90, 20);

        quantidade.setText("Quantidade");
        quantidade.setBounds(190, 370, 90, 20);
        quantidade1.setBounds(260, 370, 90, 20);

        finalizar.setText("Finalizar Pedido");
        finalizar.setBounds(450, 410, 124, 25);
        finalizar.addActionListener(this);
        finalizar.addActionListener(new finalizar());
        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });

        adicionar.setText("Adicionar Item");
        adicionar.setBounds(130, 410, 124, 25);
        adicionar.addActionListener(this);
        adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (feminino.isSelected()) {
                    hamburgueria.setSexo("Feminino");
                }
                if (masculino.isSelected()) {
                    hamburgueria.setSexo("Masculino");
                }
                hamburgueria.setFormaPagamento(String.valueOf(comboPagamento.getSelectedItem()));

                System.out.println(comboPagamento.getSelectedItem());

                hamburgueria.setNome(nome1.getText());
                hamburgueria.setCpf(cpf1.getText());
                hamburgueria.setItem(item1.getText());
                hamburgueria.setPreco(Double.parseDouble(preco1.getText()));
                hamburgueria.setQuantidade(Integer.parseInt(quantidade1.getText()));
                repository.insert(hamburgueria);
                nome1.setText(null);
                cpf1.setText(null);
                item1.setText(null);
                preco1.setText(null);
                quantidade1.setText(null);
                totalPedido1.setText(Double.toString(hamburgueria.getTotalPedido()));
                idHamburguer = 0;
                totalPedido2 = 0;
            }
        });

        painel.add(feminino);
        painel.add(masculino);

        consultar.setText("Consultar");
        consultar.setBounds(275, 410, 90, 25);
        consultar.addActionListener(new consultaAction());

        remover.setText("Remover Item");
        remover.setBounds(580, 410, 114, 25);
        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                try {
                    repository.remover((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                model.removeRow(linhaSelecionada);
            }

        });

        tabelaPedidos.setText("Tabela de Pedidos");
        tabelaPedidos.setBounds(400, 140, 150, 15);

        totalPedido.setText("Total do Pedido:");
        totalPedido.setBounds(400, 370, 140, 20);
        totalPedido1.setBounds(500, 370, 140, 20);

        tabela.setBounds(398, 170, 340, 190);
        tabela.setAutoCreateRowSorter(true);
        tabela.setAutoscrolls(true);

        painel.setLayout(null);
        painel.add(imagem);
        painel.add(dadoscliente);
        painel.add(nome);
        painel.add(nome1);
        painel.add(cpf);
        painel.add(cpf1);
        painel.add(sexo);
        painel.add(feminino);
        painel.add(masculino);
        painel.add(formaPagamento);
        painel.add(cartaocredito);
        painel.add(dinheiro);
        painel.add(pix);
        painel.add(comboPagamento);
        painel.add(dadositem);
        painel.add(item);
        painel.add(item1);
        painel.add(preco);
        painel.add(preco1);
        painel.add(quantidade);
        painel.add(quantidade1);
        painel.add(finalizar);
        painel.add(adicionar);
        painel.add(consultar);
        painel.add(remover);
        painel.add(tabela);
        painel.add(totalPedido);
        painel.add(totalPedido1);
        painel.add(tabelaPedidos);
        frame.add(painel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hamburgueria.setNome(nome1.getText());
        hamburgueria.setCpf(cpf1.getText());
        hamburgueria.setItem(item1.getText());
        hamburgueria.setPreco(Double.parseDouble(preco1.getText()));
        hamburgueria.setQuantidade(Integer.parseInt(quantidade1.getText()));
        if (idHamburguer == 0) {
            repository.insert(hamburgueria);
        } else {
            try {
                repository.finalizar(hamburgueria);
            } catch (Exception e1) {
            }
        }
        nome1.setText(null);
        cpf1.setText(null);
        sexo.setText("feminino");
        sexo.setText("masculino");
        formaPagamento.setText(null);
        item1.setText(null);
        preco1.setText(null);
        quantidade1.setText(null);
        totalPedido1.setText(Double.toString(hamburgueria.getTotalPedido()));
        idHamburguer = 0;
        totalPedido2 = 0;
        tabela.setModel(model);
        new consultaAction().actionPerformed(e);
    }

    public class consultaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            while (tabela.getModel().getRowCount() > 0) {
                ((DefaultTableModel) tabela.getModel()).setRowCount(0);
                ((DefaultTableModel) tabela.getModel()).setColumnCount(0);
            }

            ArrayList<Hamburgueria> hamburguerias = new ArrayList<>();

            try {
                hamburguerias = repository.consulta();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            model.addColumn("Código");
            model.addColumn("Item");
            model.addColumn("Qtd");
            model.addColumn("Valor");
            model.addRow(new Object[] { "Código", "Item", "Qtd", "Valor" });

            for (int i = 0; i < hamburguerias.size(); i++) {
                hamburgueria = hamburguerias.get(i);
                model.addRow(new Object[] { hamburgueria.getCodigo(), hamburgueria.getItem(),
                        hamburgueria.getQuantidade(), hamburgueria.getPreco(),
                        hamburgueria.getNome(), hamburgueria.getCpf(),
                        hamburgueria.getSexo(), hamburgueria.getFormaPagamento(),
                        hamburgueria.getTotalPedido() });
            }

            tabela.setModel(model);
        }

    }

    public class finalizar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Hamburgueria h = new Hamburgueria();
            try {
                h = repository.findById((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
                nome1.setText(h.getNome());
                cpf1.setText(h.getCpf());
                item1.setText(h.getItem());
                preco1.setText(String.valueOf(h.getPreco()));
                quantidade1.setRows(h.getQuantidade());
                totalPedido1.setText(String.valueOf(h.getTotalPedido()));
                idHamburguer = h.getCodigo();

            } catch (SQLException e1) {

            }

        }

    }

}