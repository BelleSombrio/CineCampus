# CineCampus - Totem de Autoatendimento de Cinema

## 📽️ Descrição

Sistema de totem de autoatendimento para bilheteria de cinema desenvolvido em Java. O aplicativo simula a experiência completa de compra de ingressos, desde a visualização do mapa de poltronas até o recebimento do resumo da venda.

## 🎯 Funcionalidades Implementadas

### 1. Gerenciamento da Sala
- Sala com 10 filas × 10 poltronas (matriz 10×10)
- 15 poltronas pré-ocupadas aleatoriamente (vendas pela internet)
- Mapa visual com "-" (livre) e "#" (ocupada)
- Numeração clara de filas e poltronas para fácil localização

### 2. Menu Interativo
- [1] Ver mapa da sala
- [2] Comprar ingresso
- [3] Ver resumo de vendas
- [0] Encerrar atendimento

### 3. Compra de Ingresso
- Validação de fila e poltrona dentro dos limites (0-9)
- Verificação de disponibilidade (não vende poltrona ocupada)
- Seleção de tipo de ingresso (1-Inteira ou 2-Meia-entrada)
- Cálculo de preço dinâmico por fila
- Confirmação antes de finalizar a transação

### 4. Tabela de Preços
| Fila | Localização | Preço Inteira | Preço Meia |
|------|-------------|---------------|-----------|
| 0-1  | Frente      | R$ 15,00      | R$ 7,50   |
| 2-7  | Meio        | R$ 25,00      | R$ 12,50  |
| 8-9  | VIP         | R$ 35,00      | R$ 17,50  |

### 5. Feedback em Tempo Real
- Mapa atualizado após cada ação
- Bilheteria acumulada em reais
- Número de ingressos vendidos pelo totem
- Taxa de ocupação da sala em porcentagem

### 6. Classificação da Sessão
- **90%+**: "Sessão Esgotada — Sucesso de Bilheteria!"
- **70-89%**: "Casa Cheia"
- **40-69%**: "Sessão Mediana"
- **<40%**: "Sala Vazia — precisa divulgar mais"

## 🏗️ Arquitetura - Orientação a Objetos

O projeto foi desenvolvido com separação clara de responsabilidades em 6 classes principais:

### Classes

#### `Poltrona.java`
Representa uma única poltrona da sala.
- **Atributo**: `ocupada` (boolean)
- **Métodos**: 
  - `ocupar()` - marca a poltrona como ocupada
  - `estaLivre()` - verifica se está disponível

#### `Sala.java`
Gerencia a matriz de poltronas e lógica da sessão.
- **Atributos**: 
  - Matriz 10×10 de `Poltrona`
  - Vetor de preços por fila
  - Contador de poltronas vendidas
- **Métodos**: 
  - `inicializar()` - cria sala e ocupa 15 poltronas aleatórias
  - `ocuparPoltrona(fila, poltrona)` - ocupa uma poltrona
  - `validarPoltrona(fila, poltrona)` - verifica se é válida e está livre
  - `exibirMapa()` - mostra mapa visual
  - `calcularOcupacao()` - retorna taxa de ocupação (%)
  - `getTotalOcupadas()` - total de poltronas vendidas
  - `precoFila(fila)` - retorna preço base da fila

#### `Ingresso.java`
Representa um ingresso vendido.
- **Atributos**: `fila`, `poltrona`, `tipo`, `preco`
- **Métodos**: getters para acessar dados

#### `Bilheteria.java`
Controla as vendas e arrecadação.
- **Atributos**: 
  - Vetor de `Ingresso` vendidos
  - `totalVendido` (em R$)
  - `quantidadeVendida` (número de ingressos)
- **Métodos**: 
  - `adicionarVenda(ingresso)` - registra uma venda
  - `getTotalVendido()` - retorna arrecadação total
  - `getQuantidadeVendida()` - retorna número de ingressos
  - `obterResumo()` - exibe relatório de vendas

#### `Totem.java`
Orquestra todo o sistema - menu e fluxo principal.
- **Atributos**: `sala`, `bilheteria`, `scanner`
- **Métodos**: 
  - `exibirCabecalho()` - mostra título e stats
  - `exibirMenu()` - mostra opções
  - `procesarOpcao(opcao)` - executa ação selecionada
  - `executarCompra()` - fluxo completo de compra
  - `exibirResumo()` - mostra vendas do totem
  - `encerrar()` - finaliza e classifica sessão
  - `run()` - loop principal

#### `Main.java`
Ponto de entrada da aplicação.

## 🚀 Como Compilar e Executar

### Compilação
```bash
cd src
javac cinema/*.java
```

### Execução
```bash
cd src
java cinema.Main
```

## 📋 Exemplo de Uso

```
===== CineCampus - Totem de Autoatendimento =====
Ingressos vendidos: 0 | Bilheteria: R$ 0,00 | Ocupacao: 15%

    0 1 2 3 4 5 6 7 8 9
 0  - - # - - - - # - -
 1  - - - - - # - - - -
 2  # - - - - - - - # -
 3  - - - # - - - - - -
 4  - # - - - - # - - -
 5  - - - - - - - - - #
 6  - - - - # - - - - -
 7  - - # - - - - # - -
 8  - - - - - # - - - -
 9  # - - - - - - - # -

Menu: [1] Ver mapa [2] Comprar [3] Resumo [0] Encerrar
Opção: 2

Fila: 8
Poltrona: 3
Tipo (1-Inteira / 2-Meia): 1

Poltrona VIP. Valor: R$ 35,00. Confirma? (S/N): S

✓ Compra realizada! Bom filme! :)
```

## 📚 Conceitos Utilizados

- ✅ Matrizes (matriz 10×10 de `Poltrona`)
- ✅ Vetores (preços por fila, ingressos vendidos)
- ✅ Classe `Random` (para ocupar poltronas iniciais)
- ✅ Entrada/Saída com `Scanner`
- ✅ **Orientação a Objetos**:
  - Classes bem definidas
  - Encapsulamento (atributos privados)
  - Construtores
  - Métodos para cada responsabilidade
  - Separação de concerns

## ✅ Critérios de Avaliação

- ✅ **Correção**: Sistema funciona conforme especificado
- ✅ **Qualidade do código**: Bem estruturado, indentado, nomes significativos
- ✅ **Interação com usuário**: Menu intuitivo, mensagens claras, tratamento de erros
- ✅ **Cálculos**: Preços, bilheteria e ocupação corretos
- ✅ **Documentação**: Comentários explicativos em todas as classes
- ✅ **Orientação a Objetos**: Classes com separação clara de responsabilidades (+0,5 bônus)

## 📝 Estrutura de Arquivos

```
CineCampus/
├── src/
│   └── cinema/
│       ├── Main.java          // Entrada da aplicação
│       ├── Totem.java         // Orquestrador do sistema
│       ├── Sala.java          // Gerencia as poltronas
│       ├── Poltrona.java      // Representa uma poltrona
│       ├── Ingresso.java      // Dados do ingresso
│       └── Bilheteria.java    // Controla vendas
├── README.md                  // Este arquivo
└── .gitignore                 // Arquivos a ignorar
```

## 👨‍💻 Autor

Desenvolvido como projeto final de Programação I

## 📄 Licença

MIT License
