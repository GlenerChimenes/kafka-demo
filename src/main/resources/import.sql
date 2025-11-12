INSERT INTO portadores (id, nome_completo, cpf, password) VALUES (100, 'Carlos Henrique Silva', '75742849036', '$2a$10$2qhPu70lsjB8.KyuggwD/eUXyChTTunA7aQ0whq2D1gukDFuNw2Fy');
INSERT INTO portadores (id, nome_completo, cpf, password) VALUES (200, 'Mariana Souza Costa', '98765432100', '$2a$10$2qhPu70lsjB8.KyuggwD/eUXyChTTunA7aQ0whq2D1gukDFuNw2Fy');
INSERT INTO portadores (id, nome_completo, cpf, password) VALUES (300, 'Fernanda Oliveira', '11122233344', '$2a$10$2qhPu70lsjB8.KyuggwD/eUXyChTTunA7aQ0whq2D1gukDFuNw2Fy');

INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (100, '12345678901', '000123', '001', 2500.00, 'ATIVA', false);
INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (200, '98765432100', '000456', '001', 15000.50, 'ATIVA', false);
INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (300, '11122233344', '000789', '002', 320.75, 'BLOQUEADA', true);

INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (1, '000123', 'DEPOSITO', 500.00, '2025-11-01T10:00:00Z', 'Depósito inicial');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (2, '000123', 'SAQUE', 200.00, '2025-11-03T14:30:00Z', 'Saque no caixa eletrônico');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (3, '000456', 'DEPOSITO', 2000.00, '2025-11-02T09:15:00Z', 'Depósito salário');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (4, '000789', 'DEPOSITO', 300.00, '2025-10-30T11:45:00Z', 'Depósito poupança');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (100, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (200, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (300, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (200, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (100, 2);

