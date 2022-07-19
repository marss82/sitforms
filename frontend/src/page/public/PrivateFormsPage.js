/* eslint-disable */
import React, {useContext, useEffect} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import {fetchPrivateForms} from "../../http/formAPI";
import FormPrivateTable from "../../component/table/FormPrivateTable";

const PrivateFormsPage = observer(() => {
    const {form} = useContext(Context)
    const {user} = useContext(Context)
    useEffect(() => {
        fetchPrivateForms(user.id).then(data => {
            form.setForms(data)
            console.log(data)
        })
    }, [])
    return (
        <div className="forms">
            <div className="wrapper">
                <h1>Assigned forms</h1>

                <FormPrivateTable></FormPrivateTable>

            </div>
        </div>
    );
});

export default PrivateFormsPage;