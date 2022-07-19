/* eslint-disable */

import React from 'react';

const FormItem = ({formEntity}) => {
    console.log(formEntity)
    return (
        <div className="form-item">
            <div className="row">
                <div className="col-md-7">
                    <p>{formEntity.formName}</p>
                </div>
                <div className="col-md-5">
                    <a href={'/form/'+formEntity.id}>Fill in a form</a>
                    <a href={'/form/'+formEntity.id + '/users'}>Edit members</a>
                    <a href={'/form/'+formEntity.id+'/statistics'}>Statistics</a>
                </div>
            </div>
        </div>
    );
};

export default FormItem;