# 🧪 Sistema de Controle de Reagentes

👥 **Nossa equipe**  
Estevam Melo RM555124  
Eduardo Lima RM554804  
Guilherme Ulacco RM558418  
Matheus Hostim RM556517  

💰 **Objetivo do Projeto**  
Este projeto tem como objetivo criar uma API REST em Java com Spring Boot para gerenciar reagentes químicos em um laboratório. A ideia é permitir cadastrar reagentes, consultar informações, atualizar e deletar registros, incluindo dados do fornecedor de cada reagente. O foco é automatizar o controle de estoque, facilitando o monitoramento de validade, quantidade e lote dos reagentes, além de manter o vínculo com fornecedores.

---

🔍 **Funcionalidades da API**

**Listar todos os reagentes**  
`GET /reagentes` → Retorna a lista completa de reagentes cadastrados.

**Buscar reagente por ID**  
`GET /reagentes/{id}` → Retorna os dados de um reagente específico.

**Criar reagente**  
`POST /reagentes` → Cria um novo reagente.  
Exemplo de JSON de entrada:
```json
{
  "id": null,
  "nome": "Ácido Clorídrico",
  "quantidade": 10,
  "lote": "L1234",
  "validade": "2026-12-31",
  "fornecedor": {
    "id": null,
    "nome": "LabFornecedores",
    "contato": "contato@labfornecedores.com"
  }
}

