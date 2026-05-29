package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Cliente;
import service.ClienteService;

public class TelaSistema extends JFrame {

	private ClienteService service = new ClienteService();

	private JPanel contentPanel;

	public TelaSistema() {

		if (!realizarLogin()) {
			JOptionPane.showMessageDialog(
					null,
					"Login inválido."
					);
			System.exit(0);
		}

		configurarJanela();

		configurarMenuLateral();

		telaHome();

		setVisible(true);
	}

	/*
	 * =========================
	 * LOGIN
	 * =========================
	 */

	private boolean realizarLogin() {

		JTextField campoNumero = new JTextField();

		JPasswordField campoSenha =
				new JPasswordField();

		campoNumero.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		campoSenha.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(4, 1, 10, 10));

		panel.add(new JLabel("Número:"));
		panel.add(campoNumero);

		panel.add(new JLabel("Senha:"));
		panel.add(campoSenha);

		int resultado = JOptionPane.showConfirmDialog(
				null,
				panel,
				"Login",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE
				);

		if (resultado != JOptionPane.OK_OPTION) {
			return false;
		}

		try {

			int numero =
					Integer.parseInt(campoNumero.getText());

			String senha =
					new String(campoSenha.getPassword());

			return service.login(numero, senha);

		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * =========================
	 * CONFIGURAÇÃO DA JANELA
	 * =========================
	 */

	private void configurarJanela() {

		setTitle("ED-C3");

		setSize(1200, 750);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		getContentPane().setBackground(
				new Color(240, 244, 248)
				);
	}

	/*
	 * =========================
	 * MENU LATERAL
	 * =========================
	 */

	private void configurarMenuLateral() {

		JPanel menu = new JPanel();

		menu.setLayout(new GridLayout(10, 1, 12, 12));

		menu.setPreferredSize(new Dimension(240, 0));

		menu.setBorder(new EmptyBorder(25, 20, 25, 20));

		menu.setBackground(new Color(15, 23, 42));

		JLabel logo = new JLabel("ED-C3");

		logo.setForeground(Color.WHITE);

		logo.setFont(
				new Font("SansSerif", Font.BOLD, 30)
				);

		JButton btnCadastrar =
				criarBotao("Cadastrar");

		JButton btnConsultar =
				criarBotao("Consultar");

		JButton btnAtualizar =
				criarBotao("Atualizar");

		JButton btnRemover =
				criarBotao("Remover");

		JButton btnFaixa =
				criarBotao("Faixa Etária");

		JButton btnSexo =
				criarBotao("Contagem Sexo");

		JButton btnNovo =
				criarBotao("Mais Jovem");

		JButton btnVelho =
				criarBotao("Mais Velho");

		btnCadastrar.addActionListener(
				e -> telaCadastro()
				);

		btnConsultar.addActionListener(
				e -> telaConsulta()
				);

		btnAtualizar.addActionListener(
				e -> telaAtualizacao()
				);

		btnRemover.addActionListener(
				e -> telaRemocao()
				);

		btnFaixa.addActionListener(
				e -> telaFaixaEtaria()
				);

		btnSexo.addActionListener(
				e -> telaContagemSexo()
				);

		btnNovo.addActionListener(
				e -> telaMaisNovo()
				);

		btnVelho.addActionListener(
				e -> telaMaisVelho()
				);

		menu.add(logo);

		menu.add(btnCadastrar);
		menu.add(btnConsultar);
		menu.add(btnAtualizar);
		menu.add(btnRemover);
		menu.add(btnFaixa);
		menu.add(btnSexo);
		menu.add(btnNovo);
		menu.add(btnVelho);

		add(menu, BorderLayout.WEST);
	}

	/*
	 * =========================
	 * BOTÃO PADRÃO
	 * =========================
	 */

	private JButton criarBotao(String texto) {

		JButton btn = new JButton(texto);

		btn.setFocusPainted(false);

		btn.setBackground(new Color(37, 99, 235));

		btn.setForeground(Color.WHITE);

		btn.setFont(
				new Font("SansSerif", Font.BOLD, 15)
				);

		btn.setCursor(
				new Cursor(Cursor.HAND_CURSOR)
				);

		btn.setPreferredSize(
				new Dimension(200, 45)
				);

		return btn;
	}

	/*
	 * =========================
	 * HOME
	 * =========================
	 */

	private void telaHome() {

		JPanel panel = criarPainelBase();

		JLabel titulo =
				new JLabel("Sistema Bancário");

		titulo.setFont(
				new Font("SansSerif", Font.BOLD, 34)
				);

		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(Box.createVerticalGlue());

		panel.add(titulo);

		panel.add(Box.createVerticalGlue());

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * CADASTRO
	 * =========================
	 */

	private void telaCadastro() {

		JPanel panel =
				criarPainelFormulario("Cadastrar Cliente");

		JTextField txtNome = criarCampo();

		JTextField txtCpf = criarCampo();

		JTextField txtIdade = criarCampo();

		JComboBox<String> sexoBox =
				new JComboBox<>(new String[]{"M", "F"});

		sexoBox.setMaximumSize(
				new Dimension(300, 40)
				);

		sexoBox.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		JTextField txtSaldo = criarCampo();

		JButton salvar = criarBotao("Salvar");

		JLabel resultado = criarLabelResultado();

		salvar.addActionListener(e -> {

			try {

				boolean ok =
						service.cadastrarCliente(
								txtNome.getText(),
								txtCpf.getText(),
								Integer.parseInt(
										txtIdade.getText()
										),
								sexoBox.getSelectedItem()
								.toString().charAt(0),
								Double.parseDouble(
										txtSaldo.getText()
										)
								);

				if (ok) {
					resultado.setText(
							"Cliente cadastrado."
							);
				} else {
					resultado.setText(
							"Erro ao cadastrar."
							);
				}

			} catch (Exception ex) {

				resultado.setText(
						"Dados inválidos."
						);
			}
		});

		adicionarCampo(panel, "Nome", txtNome);

		adicionarCampo(panel, "CPF", txtCpf);

		adicionarCampo(panel, "Idade", txtIdade);

		adicionarCampo(panel, "Sexo", sexoBox);

		adicionarCampo(panel, "Saldo", txtSaldo);

		panel.add(Box.createVerticalStrut(20));

		salvar.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(salvar);

		panel.add(Box.createVerticalStrut(15));

		panel.add(resultado);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * CONSULTA
	 * =========================
	 */

	private void telaConsulta() {

		JPanel panel =
				criarPainelFormulario("Consultar Cliente");

		JTextField txtNome = criarCampo();

		JButton buscar = criarBotao("Buscar");

		JTextArea area = new JTextArea();

		area.setEditable(false);

		area.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		area.setLineWrap(true);

		area.setWrapStyleWord(true);

		JScrollPane scroll =
				new JScrollPane(area);

		scroll.setPreferredSize(
				new Dimension(600, 300)
				);

		buscar.addActionListener(e -> {

			Cliente cliente =
					service.buscarCliente(
							txtNome.getText()
							);

			if (cliente != null) {
				area.setText(cliente.toString());
			} else {
				area.setText(
						"Cliente não encontrado."
						);
			}
		});

		adicionarCampo(panel, "Nome", txtNome);

		panel.add(Box.createVerticalStrut(15));

		panel.add(buscar);

		panel.add(Box.createVerticalStrut(20));

		panel.add(scroll);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * COMPONENTES AUXILIARES
	 * =========================
	 */

	private JTextField criarCampo() {

		JTextField campo = new JTextField();

		campo.setMaximumSize(
				new Dimension(350, 42)
				);

		campo.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		return campo;
	}

	private JLabel criarLabelResultado() {

		JLabel label = new JLabel();

		label.setFont(
				new Font("SansSerif", Font.BOLD, 15)
				);

		label.setAlignmentX(Component.LEFT_ALIGNMENT);

		return label;
	}

	private void adicionarCampo(
			JPanel panel,
			String texto,
			JComponent componente
			) {

		JLabel label = new JLabel(texto);

		label.setFont(
				new Font("SansSerif", Font.BOLD, 16)
				);

		label.setAlignmentX(Component.LEFT_ALIGNMENT);

		componente.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(label);

		panel.add(Box.createVerticalStrut(5));

		panel.add(componente);

		panel.add(Box.createVerticalStrut(15));
	}

	/*
	 * =========================
	 * PAINÉIS
	 * =========================
	 */

	private JPanel criarPainelBase() {

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setBorder(
				new EmptyBorder(40, 40, 40, 40)
				);

		panel.setLayout(
				new BoxLayout(panel, BoxLayout.Y_AXIS)
				);

		return panel;
	}

	private JPanel criarPainelFormulario(String titulo) {

		JPanel panel = criarPainelBase();

		JLabel labelTitulo =
				new JLabel(titulo);

		labelTitulo.setFont(
				new Font("SansSerif", Font.BOLD, 30)
				);

		labelTitulo.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(labelTitulo);

		panel.add(Box.createVerticalStrut(30));

		return panel;
	}

	/*
	 * =========================
	 * TROCA DE TELAS
	 * =========================
	 */

	private void atualizarTela(JPanel panel) {

		if (contentPanel != null) {
			remove(contentPanel);
		}

		contentPanel = panel;

		add(contentPanel, BorderLayout.CENTER);

		revalidate();

		repaint();
	}
	/*
	 * =========================
	 * ATUALIZAÇÃO
	 * =========================
	 */

	private void telaAtualizacao() {

		JPanel panel =
				criarPainelFormulario(
						"Atualizar Cliente"
						);

		JTextField txtNome = criarCampo();

		JTextField txtIdade = criarCampo();

		JTextField txtSexo = criarCampo();

		JTextField txtSaldo = criarCampo();

		JButton atualizar =
				criarBotao("Atualizar");

		JLabel resultado =
				criarLabelResultado();

		JLabel aviso = new JLabel(
				"Deixe vazio para manter o valor atual."
				);

		aviso.setFont(
				new Font("SansSerif", Font.ITALIC, 14)
				);

		aviso.setForeground(Color.GRAY);

		atualizar.addActionListener(e -> {

			String nome =
					txtNome.getText();

			if (nome.isBlank()) {

				resultado.setText(
						"Informe o nome."
						);

				return;
			}

			Cliente cliente =
					service.buscarCliente(nome);

			if (cliente == null) {

				resultado.setText(
						"Cliente não encontrado."
						);

				return;
			}

			try {

				/*
				 * IDADE
				 */

				int idade =
						txtIdade.getText().isBlank()
						? cliente.getIdade()
								: Integer.parseInt(
										txtIdade.getText()
										);

				/*
				 * SEXO
				 */

				char sexo =
						txtSexo.getText().isBlank()
						? cliente.getSexo()
								: txtSexo.getText()
								.toUpperCase()
								.charAt(0);

				if (sexo != 'M'
						&& sexo != 'F') {

					resultado.setText(
							"Sexo inválido."
							);

					return;
				}

				/*
				 * SALDO
				 */

				double saldo =
						txtSaldo.getText().isBlank()
						? cliente.getSaldo()
								: Double.parseDouble(
										txtSaldo.getText()
										);

				boolean ok =
						service.atualizarCliente(
								nome,
								idade,
								sexo,
								saldo
								);

				if (ok) {

					resultado.setText(
							"Cliente atualizado."
							);

				} else {

					resultado.setText(
							"Erro ao atualizar."
							);
				}

			} catch (Exception ex) {

				resultado.setText(
						"Dados inválidos."
						);
			}
		});

		adicionarCampo(panel, "Nome", txtNome);

		adicionarCampo(
				panel,
				"Nova idade",
				txtIdade
				);

		adicionarCampo(
				panel,
				"Novo sexo (M/F)",
				txtSexo
				);

		adicionarCampo(
				panel,
				"Novo saldo",
				txtSaldo
				);

		panel.add(Box.createVerticalStrut(5));

		aviso.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(aviso);

		panel.add(Box.createVerticalStrut(20));

		atualizar.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(atualizar);

		panel.add(Box.createVerticalStrut(15));

		resultado.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(resultado);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * REMOÇÃO
	 * =========================
	 */

	private void telaRemocao() {

		JPanel panel =
				criarPainelFormulario("Remover Cliente");

		JTextField txtNome = criarCampo();

		JButton remover =
				criarBotao("Remover");

		JLabel resultado =
				criarLabelResultado();

		remover.addActionListener(e -> {

			boolean ok =
					service.removerCliente(
							txtNome.getText()
							);

			if (ok) {
				resultado.setText(
						"Cliente removido."
						);
			} else {
				resultado.setText(
						"Cliente não encontrado."
						);
			}
		});

		adicionarCampo(panel, "Nome", txtNome);

		panel.add(Box.createVerticalStrut(20));

		panel.add(remover);

		panel.add(Box.createVerticalStrut(15));

		panel.add(resultado);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * FAIXA ETÁRIA
	 * =========================
	 */

	private void telaFaixaEtaria() {

		JPanel panel =
				criarPainelFormulario("Faixa Etária");

		JTextField txtMin = criarCampo();

		JTextField txtMax = criarCampo();

		JButton buscar =
				criarBotao("Buscar");

		JTextArea area = new JTextArea();

		area.setEditable(false);

		area.setFont(
				new Font("SansSerif", Font.PLAIN, 15)
				);

		JScrollPane scroll =
				new JScrollPane(area);

		scroll.setPreferredSize(
				new Dimension(700, 300)
				);

		buscar.addActionListener(e -> {

			try {

				int min =
						Integer.parseInt(
								txtMin.getText()
								);

				int max =
						Integer.parseInt(
								txtMax.getText()
								);

				area.setText(
						service.listarFaixaEtaria(
								min,
								max
								)
						);

			} catch (Exception ex) {

				area.setText(
						"Valores inválidos."
						);
			}
		});

		adicionarCampo(panel, "Idade mínima", txtMin);

		adicionarCampo(panel, "Idade máxima", txtMax);

		panel.add(Box.createVerticalStrut(20));

		panel.add(buscar);

		panel.add(Box.createVerticalStrut(20));

		panel.add(scroll);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * CONTAGEM SEXO
	 * =========================
	 */

	private void telaContagemSexo() {

		JPanel panel =
				criarPainelBase();

		JLabel masc = new JLabel(
				"Masculino: "
						+ service.contarMasculino()
				);

		JLabel fem = new JLabel(
				"Feminino: "
						+ service.contarFeminino()
				);

		masc.setFont(
				new Font("SansSerif", Font.BOLD, 22)
				);

		fem.setFont(
				new Font("SansSerif", Font.BOLD, 22)
				);

		panel.add(masc);

		panel.add(Box.createVerticalStrut(20));

		panel.add(fem);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * MAIS NOVO
	 * =========================
	 */

	private void telaMaisNovo() {

		JPanel panel =
				criarPainelFormulario("Cliente Mais Jovem");

		Cliente cliente =
				service.clienteMaisNovo();

		JTextArea area = new JTextArea();

		area.setEditable(false);

		area.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		if (cliente != null) {
			area.setText(cliente.toString());
		} else {
			area.setText("Nenhum cliente.");
		}

		panel.add(area);

		atualizarTela(panel);
	}

	/*
	 * =========================
	 * MAIS VELHO
	 * =========================
	 */

	private void telaMaisVelho() {

		JPanel panel =
				criarPainelFormulario("Cliente Mais Velho");

		Cliente cliente =
				service.clienteMaisVelho();

		JTextArea area = new JTextArea();

		area.setEditable(false);

		area.setFont(
				new Font("SansSerif", Font.PLAIN, 16)
				);

		if (cliente != null) {
			area.setText(cliente.toString());
		} else {
			area.setText("Nenhum cliente.");
		}

		panel.add(area);

		atualizarTela(panel);
	}
}