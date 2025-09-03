        package model;
        

                

import java.util.UUID;

public class Cliente {
                        private  UUID id;
        private String nome;
        private String email;
                private String documento;
                                
                public Cliente(String nome, String email, String documento) {


                        this.id = UUID.randomUUID();
                                this.nome = nome;
                                        this.email = email;
                                                this.documento = documento;
                        }
    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }   

    
    
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDocumento() { return documento; }

    
}