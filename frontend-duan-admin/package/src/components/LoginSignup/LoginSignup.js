import './LoginSignup.css'
import {Link} from 'react-router-dom'
import email_icon from '../Assets/email.png'
import password_icon from '../Assets/password.png'
import { useState } from 'react';
import './LoginValidation'
import LoginValidation from './LoginValidation';

const LoginSignup = () => {
    const [values, setValues] = useState({
        email: '',
        password: ''
    })

    const [errors, setErrors] = useState({})
    const handleInput = (event) => {
        setValues(prev => ({...prev, [event.target.name]: [event.target.values]}))
    }
    const handleSubmit = (event) => {
        event.preventDefault();
        setValues(LoginValidation(values))
    }

        return (
            <div className='d-flex justify-content-center align-items-center bg-info vh-100'>
                <div className='bg-white p-3 rounded w-25'>
                <h2>Sign-In</h2>
                    <form action='' onSubmit={handleSubmit}>
                        <div className='mb-3'>
                            <img className='m-2' src={email_icon} alt='' />
                            <label htmlFor='email'><strong>Email</strong></label>
                            <input type='email' placeholder='Enter email' name='email' onChange={handleInput} className='form-control rounded-0'/>
                            {errors.email&&<span className='text-danger'>{errors.email}</span>}
                        </div>
                        <div className='mb-3'>
                            <img className='m-2' src={password_icon} alt='' />  
                            <label htmlFor='password'><strong>Password</strong></label>
                            <input type='password' placeholder='Enter Password' name='password' onChange={handleInput} className='form-control rounded-0'/>
                            {errors.password&&<span className='text-danger'>{errors.password}</span>}

                        </div>
                        <button type='submit' className='btn btn-success w-100 rounded-0'>Log in</button>
                        
                        <Link to="/signup" className='btn btn-default border w-100 bg-light rounded-0 text-decoration-none'>Create Account</Link>
                    </form>
                </div>
            </div>
        );
    };


export default LoginSignup;