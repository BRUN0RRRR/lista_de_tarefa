import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp extends JFrame {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaTarefas;
    private JTextField txtTarefa;
    private JButton btnAdicionar, btnEditar, btnRemover, btnLimpar;

    public ToDoListApp() {
        setTitle("Lista de Tarefas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo para a lista
        modeloLista = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaTarefas);

        // Campo de entrada de tarefa
        txtTarefa = new JTextField(20);

        // Botões
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnRemover = new JButton("Remover");
        btnLimpar = new JButton("Limpar");

        // Painel de entrada e botões
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new FlowLayout());
        painelEntrada.add(new JLabel("Tarefa:"));
        painelEntrada.add(txtTarefa);
        painelEntrada.add(btnAdicionar);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);

        // Adiciona os componentes ao JFrame
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarTarefa();
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerTarefa();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparLista();
            }
        });
    }

    private void adicionarTarefa() {
        String tarefa = txtTarefa.getText().trim();
        if (!tarefa.isEmpty()) {
            modeloLista.addElement(tarefa);
            txtTarefa.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Insira uma tarefa válida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarTarefa() {
        int indice = listaTarefas.getSelectedIndex();
        if (indice != -1) {
            String novaTarefa = JOptionPane.showInputDialog(this, "Editar tarefa:", modeloLista.get(indice));
            if (novaTarefa != null && !novaTarefa.trim().isEmpty()) {
                modeloLista.set(indice, novaTarefa.trim());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerTarefa() {
        int indice = listaTarefas.getSelectedIndex();
        if (indice != -1) {
            modeloLista.remove(indice);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparLista() {
        if (modeloLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A lista já está vazia.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja limpar todas as tarefas?", "Confirmar Limpeza", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                modeloLista.clear();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp().setVisible(true);
            }
        });
    }
}
