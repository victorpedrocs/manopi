package cliente.model;

import java.sql.Connection;

import util.ConnectionFactory;

public class Cliente {
        
        private Integer codigo;
        private String login;
        private String senha;
        private String nome;
        private String telefone;
        private String endereco;
        
        public Cliente(Integer codigo, String login, String nome, String telefone, String endereco) {
                this.codigo = codigo;
                this.login = login;
                this.nome = nome;
                this.telefone = telefone;
                this.endereco = endereco;
        }
        
        public Cliente(Integer codigo, String nome, String telefone, String endereco) {
            this.codigo = codigo;
            this.nome = nome;
            this.telefone = telefone;
            this.endereco = endereco;
    }
        
        public Cliente(Integer codigo, String login, String senha, String nome, String telefone, String endereco) {
            this.codigo = codigo;
            this.login = login;
            this.senha = senha;
            this.nome = nome;
            this.telefone = telefone;
            this.endereco = endereco;
    }
        
        public Cliente(String login, String senha) {
			this.login = login;
			this.senha = senha;
		}
        
        
        
        public Boolean validaLogin(){
        	if (this.login != null && this.senha != null) {
				Connection connection = ConnectionFactory.getConnection();
				return true;
				
			}
        	return false;
        }

		public Integer getCodigo() {
			return codigo;
		}

		public String getLogin() {
			return login;
		}

		public String getSenha() {
			return senha;
		}

		public String getNome() {
			return nome;
		}

		public String getTelefone() {
			return telefone;
		}

		public String getEndereco() {
			return endereco;
		}
        
        
        
        
        

}
