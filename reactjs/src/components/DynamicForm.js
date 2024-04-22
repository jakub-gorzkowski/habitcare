import React from 'react';
import Input from './Input';
import Button from './Button';
import './DynamicForm.css';

const DynamicForm = ({ fields, buttonText }) => {
    return (
        <form className="dynamic-form">
            {fields.map(field => (
                <Input key={field.name} {...field} />
            ))}
            <Button color="blue">{buttonText}</Button>
        </form>
    );
}

export default DynamicForm;