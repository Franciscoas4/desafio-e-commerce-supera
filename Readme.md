# Projeto criado para o desafio Supera

Este é um projeto para o processo seletivo da Supera.

Foi Construida uma API Rest, utilizando o Spring Boot, para cumprir os requisitos impostos, como será descrito.


## Descrição

O teste consiste em construir a camada de serviço de um pseudo ecommerce de games mobile utilizando Java.

## Requisitos

- Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente.
- O usuário poderá adicionar e remover produtos do carrinho.
- O usuário poderá ordenar os produtos por preço, popularidade (score) e ordem alfabética.
- A cada produto adicionado, deve-se somar R$ 10,00 ao frete.
- Quando o valor dos produtos adicionados ao carrinho for igual ou superior a R$ 250,00, o frete é grátis.

## Tecnologias escolhidas

Para o cumprimento do desafio, as frameworks abaixo foram escolhidas, por possuir mais familiaridade:
- Spring Boot: O Spring é uma das melhores opções para criação de API´s Rest, fácil de trabalhar e muito intuitivo. Com várias feramentas embutidas, uma maior facilidade para manutenção e com annotations que tornam a aplicação mais limpa, de fácil categorização de cada parte da aplicação e maior eficiência.
- Java 11 LTS: Foi utilizada para essa aplicação a versão 11 do Java em sua versão de longo prazo. O Java é uma linguagem de programação muito robusta e continua sendo muito utilizada no mercado.
- Maven: O Maven é uma ótima ferramenta para o gerenciamento de projetos, sua facilidade para utilizar e o extenso repositório que possui, facilita a busca e implemetação de dependências que facilitam o desenvolvimento.
- H2 Database: O H2 foi utilizado por fornecer um banco de dados de teste em memoria que facilita o teste da API.
- JPA/Hibernate: O JPA/Hibernate foram utilizados para facilitar o mapeamento das entidades e criação das tabelas.
- Postman: Como uma ferramenta a parte para testes, foi utilizado o Postman, que é excelente para testar API´s Rest utilizando o formato JSON.

## Executando a API

Para execução da API, você pode utilizar a IDE de sua preferência e solicitar a inicialização do programa, ou através do comando do Maven, mvn spring-boot:run.

A API irá funcionar na porta 8080, através do endereço http://localhost:8080.

Utilizando o endereco http://localhost:8080/h2-console será aberto o banco de testes H2 onde será possível verificar as tabelas que foram criadas.

# Endpoints da API

Para utilização da API, foram criados os seguintes endpoints.

## Produtos

O primeiro passo para funcionamento da API é a inserção de produtos no banco.

---

###Inserido um produto. Método ```POST``` - /products/

http://localhost:8080/products/

Utilizando o endereço acima, juntamente com o formato JSON informado a seguir, é possível inserir produtos no banco:

```JSON
{
"name": "Toy Story",
"price": 59.99,
"score": 6,
"image": "sem imagem"
}
```
---
### Listando os produtos. Método ```GET``` - /products/

http://localhost:8080/products/

Utilizando o endereço acima, é possível listar todos os produtos que foram inseridos no banco. Também é possível, como solicitado, order por nome, preço ou score, como mostrador nos endpoints a seguir:

http://localhost:8080/products/?attrSort=NAME

http://localhost:8080/products/?attrSort=PRICE

http://localhost:8080/products/?attrSort=SCORE

---
### Listando os produtos por um id específico. Método ```GET``` - /products/{id}

Utilizando o endereço abaixo, é possível localizar um produto por id:

http://localhost:8080/products/1

---

## Carrinho

Com os produtos devidamente registrados, é possível inserir os mesmo no carrinho.

---

### Inserindo produtos no carrinho. Método ```POST``` - /carts/

Utilizando o endereço http://localhost:8080/carts/, é possível inserir um produto específico e a quantidade desejada.

Por meio de um escopo JSON, são passadas as informações necessárias, como segue:

``` JSON
[
    {
        "id": 4,
        "quantity": 1
    },
    {
        "id": 5,
        "quantity": 1
    }
]
```
---

### Adicionando mais produtos ao carrinho. Método ```PUT``` - /carts/product-add/{id}

Utilizando o endereço http://localhost:8080/carts/product-add/{id}, é possível adicionar mais produtos a um carrinho existente, seguindo o mesmo padrão de inserção utilizado no ```POST```.

---
### Listando os carrinhos existentes. Método ```GET``` - /carts/

Utilizando o endereço http://localhost:8080/carts/, mas utilizando o método ```GET```, é possível listar todos os carrinhos de compras existentes.

Além disso, assim como em produtos, é possível utilizar um QueryParameter para ordernar por nome, preço ou score.

Exemplo: http://localhost:8080/carts/?attrSort=NAME

---
### Listando um carrinho específico através do id. Método ```GET``` - /carts/{id}

Utilizando o endereço http://localhost:8080/carts/{id}, com o id do carrinho desejado, é possível trazer somente ele para vizualização.

---
### Checkout de um carrinho por id - ```GET``` - /carts/checkout/{id}

Utilizando o endereço http://localhost:8080/carts/checkout/{id}, será feito o checkout do carrinho selecionado, informando o subtotal, frete e total de produtos no carrinho.

---
### Removendo produto específicos do carrinho - ```DELETE``` /carts/product-remove/{id}

Com o endereço http://localhost:8080/shopcarts/product-remove/{id}, é possível remove produtos do carrinho pela quantidade informada, seguindo o mesmo padrão JSON informado no ```POST```.

---
### Removendo o carrinho - ```DELETE``` /carts/{id}

Com o endereço http://localhost:8080/shopcarts/{id}, você irá remover um carrinho específico e tudo o que estiver nele.