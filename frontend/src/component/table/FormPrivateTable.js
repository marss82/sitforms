/* eslint-disable */

import React, {useContext} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormItem from "../item/FormItem";
import FormPrivateItem from "../item/FormPrivateItem";

const FormPrivateTable = observer(() => {
    const {form} = useContext(Context)
    return (
        <div className="forms-row">
            {form.forms.length === 0 ?
                <p>Empty forms</p>
                :
                form.forms.map(formItem =>
                    <FormPrivateItem key={formItem.id} formEntity={formItem}/>
                )
            }
        </div>
    );
});

export default FormPrivateTable;