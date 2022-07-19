/* eslint-disable */

import React from 'react';

const FormItem = ({formEntity}) => {
    console.log(formEntity)
    return (
        <div className="form-item">
            <div className="row">
                <div className="col-md-10">
                    <p>{formEntity.formName}</p>
                </div>
                <div className="col-md-2">
                    <a href={'/form/'+formEntity.id}>Fill in a form</a>
                </div>
            </div>
        </div>
    );
};

export default FormItem;