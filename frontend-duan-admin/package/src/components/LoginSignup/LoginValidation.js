
const LoginValidation = (values) => {
    let error = {}

    if (values.email === "") {
        error.email = "Email should not be empty"
    }
    if (values.password === "") {
        error.password = "Password should not be empty"
    }

    return error;
};

export default LoginValidation;