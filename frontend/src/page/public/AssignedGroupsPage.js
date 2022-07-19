/* eslint-disable */
import React, {useContext, useEffect} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormTable from "../../component/table/FormTable";
import {fetchManagedForms} from "../../http/formAPI";
import GroupTable from "../../component/table/GroupTable";
import {fetchAssignedGroups} from "../../http/groupAPI";

const AssignedGroupsPage = observer(() => {
    const {form} = useContext(Context)
    const {user} = useContext(Context)
    useEffect(() => {
        fetchAssignedGroups(user.id).then(data => {
            form.setForms(data)
            console.log(data)
        })
    }, [])
    return (
        <div className="forms">
            <div className="wrapper">
                <h1>Assigned groups</h1>

                <GroupTable></GroupTable>
            </div>
        </div>
    );
});

export default AssignedGroupsPage;