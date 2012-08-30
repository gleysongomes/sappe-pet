package br.ufc.si.pet.sappe.service;

import br.ufc.si.pet.sappe.dao.UsuarioDAO;
import br.ufc.si.pet.sappe.entidades.Perfil;
import br.ufc.si.pet.sappe.entidades.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean insertUsuario(Usuario usuario) {
        try {
            usuarioDAO.insert(usuario);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUsuario(Usuario usuario) {
        try {
            usuarioDAO.update(usuario);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUsuarioByEmail(Usuario usuario) {
        try {
            usuarioDAO.updateByEmail(usuario);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUsuario(Usuario usuario) {
        try {
            usuarioDAO.delete(usuario);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Usuario getUsuarioById(Long id) {
        try {

            //System.out.println("id cont1 "+id);
            Usuario user = usuarioDAO.getById(id);
            //System.out.println("id cont2 "+user.getId());
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Perfil validarUsuario(Usuario usuarioEntrada, String conta) {

        Usuario user = getUsuarioByLogin(usuarioEntrada.getLogin());
        //System.out.println("====>>" + user.getLogin());

        if (user != null) {
            Perfil perfil = null;
            if (conta.trim().equals("alu")) {
                AlunoService service = new AlunoService();
                System.out.println("====++" + user.getId());
                perfil = service.getAlunoByUsuarioId(user.getId());
            } else if (conta.trim().equals("sup")) {
                SupervisorService supervisorService = new SupervisorService();
                perfil = supervisorService.getSupervisorByUsuarioId(user.getId());
            } else if (conta.trim().equals("admin")) {
                AdministradorService service = new AdministradorService();
                perfil = service.getAdministradorByUsuarioId(user.getId());
            }
            if (perfil != null) {
                perfil.setUsuario(user);
                if (perfil.getUsuario().validaSenha(usuarioEntrada.getSenha())) {
                    return perfil;
                }
            }
        }
        return null;
    }

    public Usuario getUsuarioByLogin(String login) {
        try {
            Usuario user = usuarioDAO.getByLogin(login);
            System.out.println("======" + user.getNome());
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Usuario> getUsuariosByNome(String nome) {
        try {
            ArrayList<Usuario> users = usuarioDAO.getByNome(nome);
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario getUsuarioByEmail(String email) {
        try {
            Usuario user = usuarioDAO.getByEmail(email);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Usuario getUsuarioByCpf(String cpf) {
        try {
            Usuario user = usuarioDAO.getByCpf(cpf);
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Usuario> getAllUsuarios() {
        try {
            ArrayList<Usuario> users = usuarioDAO.getAll();
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

     public ArrayList<Usuario> getAllUsuariosAlunos() {
        try {
            ArrayList<Usuario> users = usuarioDAO.getAllAlunos();
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Long getProxId() {
        try {
            Long id = usuarioDAO.proxId();
            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0L;
    }
}
