package br.com.beibe.beans;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import br.com.beibe.dao.UserDAO;
import br.com.beibe.utils.Converter;
import br.com.beibe.utils.Validator;;

@SuppressWarnings("serial")
public abstract class User implements Bean {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private LocalDate dateBirth;
    private String phone;
    private Address address;
    private transient String password;
    private String role = getRole();

    public static User getInstanceOf(String role) {
        if (role == null)
            return null;
        String[] className = User.class.getName().split("\\.");
        className[className.length - 1] = role.substring(0, 1).toUpperCase() + role.substring(1);
        try {
            return (User) Class.forName(String.join(".", className)).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            return null;
        }
    }

    public User() {}

    public User(Long id, String firstName, String lastName, String cpf, String email, LocalDate dateBirth, String phone, Address address, String password) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCpf(cpf);
        setEmail(email);
        setDateBirth(dateBirth);
        setPhone(phone);
        setPassword(password);
    }

    public Long getId() {
        return this.id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = Converter.nullable(firstName);
    }

    public String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = Converter.nullable(lastName);
    }

    public String getCpf() {
        return this.cpf;
    }

    public final void setCpf(String cpf) {
        this.cpf = Converter.nullable(Converter.removeNonDigits(cpf));
    }

    public LocalDate getDateBirth() {
        return this.dateBirth;
    }

    public final void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public final void setEmail(String email) {
        email = Converter.nullable(email);
        if (email != null)
            email = email.toLowerCase();
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public final void setPhone(String phone) {
        this.phone = Converter.nullable(Converter.removeNonDigits(phone));
    }

    public Address getAddress() {
        return this.address;
    }

    public String getPassword() {
        return this.password;
    }

    public final void setPassword(String password) {
        this.password = Converter.nullable(password);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRole() {
        String[] clasName = getClass().getName().split("\\.");
        return clasName[clasName.length - 1].toLowerCase();
    }

    @Override
    public List<ValError> validate() {
        List<ValError> errors = new ArrayList<>();

        // Validar atributos requeridos
        if (this.firstName == null)
            errors.add(new ValError("first_name", "O campo 'NOME' é de preenchimento obrigatório"));
        if (this.lastName == null)
            errors.add(new ValError("last_name", "O campo 'SOBRENOME' é de preenchimento obrigatório"));
        if (this.cpf == null)
            errors.add(new ValError("cpf", "O campo 'CPF' é de preenchimento obrigatório"));
        if (this.email == null)
            errors.add(new ValError("email", "O campo 'EMAIL' é de preenchimento obrigatório"));
        if (this.dateBirth == null)
            errors.add(new ValError("date_birth", "O campo 'DATA DE NASCIMENTO' é de preenchimento obrigatório"));
        if (this.password == null)
            errors.add(new ValError("password1", "O campo 'SENHA' é de preenchimento obrigatório"));

        // Validar CPF
        if (this.cpf != null) {
            if (!Validator.isCpf(this.cpf))
                errors.add(new ValError("cpf", "O CPF fornecido é inválido"));
            else {
                User user = UserDAO.find(this.cpf, UserDAO.Fields.CPF);
                if (user != null && (this.id == null || !user.id.equals(this.id)))
                    errors.add(new ValError("cpf", "O CPF '" + Converter.toCpf(this.cpf) + "' já está cadastrado"));
            }
        }

        // Validar email
        if (this.email != null) {
            if (!Validator.isEmail(this.email))
                errors.add(new ValError("email", "O email fornecido é inválido"));
            else {
                User user = UserDAO.find(this.email, UserDAO.Fields.EMAIL);
                if (user != null && (this.id == null || !user.id.equals(this.id)))
                    errors.add(new ValError("email", "O email '" + this.email + "' já está cadastrado"));
            }
        }

        // Validar nome
        if (this.firstName != null) {
            if (!Validator.isName(this.firstName)) {
                errors.add(new ValError("first_name", "O campo 'NOME' contém caracteres inválidos"));
            } else if (this.firstName.length() < 2 || this.firstName.length() > 30) {
                errors.add(new ValError("first_name", "O campo 'NOME' deve ter entre 2 e 30 caracteres"));
            }
        }

        // Validar sobrenome
        if (this.lastName != null) {
            if (!Validator.isName(this.lastName)) {
                errors.add(new ValError("last_name", "O campo 'SOBRENOME' contém caracteres inválidos"));
            } else if (this.lastName.length() < 2 || this.lastName.length() > 150) {
                errors.add(new ValError("last_name", "O campo 'SOBRENOME' deve ter entre 2 e 150 caracteres"));
            }
        }

        // Validar data de nascimento
        if (this.dateBirth != null) {
            if (Period.between(this.dateBirth, LocalDate.now()).getYears() < 18)
                errors.add(new ValError("date_birth", "O usuário deve ser maior de idade (18 anos) para se cadastrar"));
        }

        // Validar telefone
        if (this.phone != null) {
            if (this.phone.length() < 10 || this.phone.length() > 11) {
                errors.add(new ValError("phone", "O telefone deve ter entre 8 e 9 digitos e prefixados com o DDD (2 digitos)"));
            }
        }

        // Validar senha
        if (this.password != null) {
            if (this.password.length() < 6 || this.password.length() > 32) {
                errors.add(new ValError("password1", "A senha deve ter entre 6 e 32 caracteres"));
            }
        }

        // VAlidar endereço
        if (this.address != null)
            this.address.validate().forEach(error -> errors.add(error));

        return errors;
    }
}
