import React from 'react';
import { Link } from 'react-router-dom'
import user_icon from '../Assets/person.png'
import email_icon from '../Assets/email.png'
import password_icon from '../Assets/password.png'

const SignUp = () => {
    return (
        <div className='d-flex justify-content-center align-items-center bg-info vh-100'>
            <div className='bg-white p-3 rounded w-25'>
            <h2>Sign-Up</h2>
                <form action=''>
                    <div className='mb-3'>
                        <img className='m-2' src={user_icon} alt='' />
                        <label htmlFor='username'><strong>UsreName</strong></label>
                        <input type='text' placeholder='Enter name' className='form-control rounded-0' />
                    </div>
                    <div className='mb-3'>
                    <img className='m-2' src={email_icon} alt='' />
                        <label htmlFor='email'><strong>Email</strong></label>
                        <input type='email' placeholder='Enter email' className='form-control rounded-0' />
                    </div>
                    <div className='mb-3'>
                    <img className='m-2' src={password_icon} alt='' />  
                        <label htmlFor='password'><strong>Password</strong></label>
                        <input type='password' placeholder='Enter Password' className='form-control rounded-0' />
                    </div>
                    <button className='btn btn-success w-100 rounded-0'>Sign Up</button>
                   
                    <Link to="/login" className='btn btn-default border w-100 bg-light rounded-0 text-decoration-none'>Login</Link>
                </form>
            </div>
        </div>
    );
};

export default SignUp;