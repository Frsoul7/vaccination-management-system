# UC3 - Definir Categoria de Tarefa

## 1. Engenharia de Requisitos

### Formato Breve

O administrativo inicia a definição de uma nova categoria de tarefa. O sistema solicita os dados necessários (i.e. descrição, área de atividade e lista de competências técnicas requeridas). O administrativo introduz os dados solicitados. O sistema valida e apresenta os dados ao administrativo, pedindo que os confirme. O administrativo confirma. O sistema regista os dados e informa o administrativo do sucesso da operação.

### Formato Completo

#### Ator principal

Administrativo

#### Partes interessadas e seus interesses
* **Administrativo:** pretende definir as categorias de tarefas para que estas possam ser usadas posteriormente na especificação de tarefas.
* **Colaborador:** pretende que as categorias estejam definidas para poder especificar corretamente as suas tarefas.
* **T4J:** pretende que a plataforma permita associar as categorias de tarefas às áreas de atividades e às tarefas.


#### Pré-condições
n/a

#### Pós-condições
A informação da categoria da tarefa é registada no sistema.

### Cenário de sucesso principal (ou fluxo básico)

1. O administrativo inicia a definição de uma nova categoria de tarefa.
2. O sistema solicita a descrição.
3. O administrativo introduz a descrição.
4. O sistema mostra a lista de áreas de atividades para que seja selecionada uma.
5. O administrativo seleciona uma área de atividade.
6. O sistema mostra a **lista de competências técnicas referentes à área de atividade previamente selecionada** para que seja selecionada uma.
7. O administrativo escolhe uma competência técnica da lista.
8. O sistema solicita indicação do seu caráter (i.e. obrigatória ou desejável).
9. O administrativo introduz informação relativa ao caráter da competência técnica.
10. **Os passos 7 a 9 repetem-se enquanto não forem introduzidas todas as competências técnicas pretendidas.**
11. O sistema valida e apresenta os dados ao administrativo, pedindo que os confirme.
12. O administrativo confirma.
13. O sistema regista os dados e informa o administrativo do sucesso da operação.


#### Extensões (ou fluxos alternativos)

*a. O administrativo solicita o cancelamento da definição da categoria de tarefa.

> O caso de uso termina.

4a. O sistema deteta que a lista de áreas de atividades está vazia.
>1. O sistema informa o administrativo de tal facto.  
>2. O sistema permite a definição de uma nova área de atividade (UC2).  
> 2a. O administrativo não define uma área de atividade. O caso de uso termina.

5a. O administrativo não encontra a área de atividade pretendida.
>1. O administrativo informa o sistema de tal facto.  
>2. O sistema permite a definição de uma nova área de atividade (UC2).  
> 2a. O administrativo não define uma área de atividade. O caso de uso termina.

6a. O sistema deteta que a lista de competências técnicas está vazia.
>1. O sistema informa o administrativo de tal facto.  
>2. O sistema permite a especificação de uma nova competência técnica (UC4).  
> 2a. O administrativo não especifica uma competência técnica. O caso de uso termina.

7a. O administrativo não encontra a competência técnica pretendida.
>1. O administrativo informa o sistema de tal facto.  
>2. O sistema permite a especificação de uma nova competência técnica (UC4).  
> 2a. O administrativo não especifica uma competência técnica. O caso de uso termina.

11a. Dados mínimos obrigatórios em falta.
>	1. O sistema informa quais os dados em falta.
>	2. O sistema permite a introdução dos dados em falta (passo 3).
>
	>	2a. O administrativo não altera os dados. O caso de uso termina.

11b. O sistema deteta que os dados (ou algum subconjunto dos dados) introduzidos devem ser únicos e que já existem no sistema.
>	1. O sistema alerta o administrativo para o facto.
>	2. O sistema permite a sua alteração (passo 3).
>
	>	2a. O administrativo não altera os dados. O caso de uso termina.

11c. O sistema deteta que os dados introduzidos (ou algum subconjunto dos dados) são inválidos.
> 1. O sistema alerta o administrativo para o facto.
> 2. O sistema permite a sua alteração (passo 3).
>
	> 2a. O administrativo não altera os dados. O caso de uso termina.


#### Requisitos especiais
\-

#### Lista de Variações de Tecnologias e Dados
\-

#### Frequência de Ocorrência
\-

#### Questões em aberto

* O identificador da categoria de tarefa tem que obedecer a alguma regra (e.g. ser sequencial)?
* Existem outros dados que são necessários?
* Todos os dados são obrigatórios?
* Qual o número de competências técnicas que no mínimo são obrigatórias para as categorias?
* Qual a frequência de ocorrência deste caso de uso?
