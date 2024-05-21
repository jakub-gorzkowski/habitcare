import React from 'react';
import axios from 'axios';
import Input from './Input';
import Button from './Button';
import './DynamicForm.css';
import { useHistory } from 'react-router-dom';

const DynamicForm = ({ fields, buttonText, action }) => {
    const history = useHistory();

    const handleSubmit = async (event) => {
        event.preventDefault();

        const data = fields.reduce((acc, field) => {
            acc[field.name] = event.target.elements[field.name].value;
            return acc;
        }, {});

        const response = await axios.post(action, data);

        if (action.endsWith('/login') && response.data.accessToken) {
            localStorage.setItem('user', JSON.stringify(response.data));
        } else if (action.endsWith('/register')) {
            history.push('/login');
        }
    };

    return (
        <form className="dynamic-form" onSubmit={handleSubmit}>
            {fields.map(field => (
                <Input key={field.name} {...field} />
            ))}
            <Button color="blue" type="submit">{buttonText}</Button>
        </form>
    );
}

export default DynamicForm;