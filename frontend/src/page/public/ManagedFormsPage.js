/* eslint-disable */
import React, {useContext, useEffect} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormTable from "../../component/table/FormTable";
import {fetchManagedForms} from "../../http/formAPI";

const ManagedFormsPage = observer(() => {
    const {form} = useContext(Context)
    const {user} = useContext(Context)
    useEffect(() => {
        fetchManagedForms(user.id).then(data => {
            form.setForms(data)
            console.log(data)
        })
    }, [])
    return (
        <div className="forms">
            <div className="wrapper">
                <h1>My created forms</h1>


                <FormTable></FormTable>


            </div>
        </div>
    );
});

export default ManagedFormsPage;