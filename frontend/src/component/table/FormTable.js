/* eslint-disable */

import React, {useContext} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormItem from "../item/FormItem";

const FormTable = observer(() => {
    const {form} = useContext(Context)
    return (
    <div className="forms-row">
        {form.forms.length === 0 ?
            <p>Empty forms</p>
            :
            form.forms.map(formItem =>
                <FormItem key={formItem.id} formEntity={formItem}/>
            )
        }
    </div>
    );
});

export default FormTable;