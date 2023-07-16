package craveiro.vinicius.crudpessoa.view.pessoa;

public  class PessoaDataProvider {

    public static String pessoaJson2Contatos() {
        return """
                {
                    "nome": "vinicius",
                    "cpf": "08626972989",
                    "nascimento": "2023-07-16T00:00:00Z",
                    "contatos": [
                        {
                            "nome": "testevaldo",
                            "telefone": "12345",
                            "email": "vinicius@craveiro.com"
                        },
                        {
                            "nome": "Josue",
                            "telefone": "5544997077071",
                            "email": "viniciuscraveiroo@gmail.com"
                        }
                    ]
                }
                """;
    }

    public static String pessoaJson1Contato() {
        return """
                {
                    "nome": "vinicius",
                    "cpf": "08626972989",
                    "nascimento": "2023-07-16T00:00:00Z",
                    "contatos": [
                        {
                            "nome": "testevaldo",
                            "telefone": "12345",
                            "email": "vinicius@craveiro.com"
                        }
                    ]
                }
                """;
    }
}
