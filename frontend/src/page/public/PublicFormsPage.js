/* eslint-disable */
import React, {useContext, useEffect} from 'react';
import {observer} from "mobx-react-lite";
import PublicFormsComponent from "../../component/PublicFormsComponent";
import {Context} from "../../index";
import {fetchManagedForms, fetchPublicForms} from "../../http/formAPI";
import FormTable from "../../component/table/FormTable";

const PublicFormsPage = observer(() => {

    const {form} = useContext(Context)
    const {user} = useContext(Context)
    useEffect(() => {
        fetchPublicForms().then(data => {
            form.setForms(data)
            console.log(data)
        })
    }, [])
    return (
        <div className="forms">
            <div className="wrapper">
                <h1>Public forms</h1>

                <FormTable></FormTable>

            </div>
        </div>
    );
});

export default PublicFormsPage;