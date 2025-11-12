INSERT INTO portadores (id, nome_completo, cpf) VALUES (1, 'Carlos Henrique Silva', '12345678901');
INSERT INTO portadores (id, nome_completo, cpf) VALUES (2, 'Mariana Souza Costa', '98765432100');
INSERT INTO portadores (id, nome_completo, cpf) VALUES (3, 'Fernanda Oliveira', '11122233344');

INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (1, '12345678901', '000123', '001', 2500.00, 'ATIVA', false);
INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (2, '98765432100', '000456', '001', 15000.50, 'ATIVA', false);
INSERT INTO contas (id, cpf_portador, numero, agencia, saldo, status, bloqueada) VALUES (3, '11122233344', '000789', '002', 320.75, 'BLOQUEADA', true);

INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (1, '000123', 'DEPOSITO', 500.00, '2025-11-01T10:00:00Z', 'Depósito inicial');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (2, '000123', 'SAQUE', 200.00, '2025-11-03T14:30:00Z', 'Saque no caixa eletrônico');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (3, '000456', 'DEPOSITO', 2000.00, '2025-11-02T09:15:00Z', 'Depósito salário');
INSERT INTO transacoes (id, conta_numero, tipo, valor, data_hora, descricao) VALUES (4, '000789', 'DEPOSITO', 300.00, '2025-10-30T11:45:00Z', 'Depósito poupança');
