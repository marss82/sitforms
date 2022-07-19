/* eslint-disable */

import React, {useContext} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormItem from "../item/FormItem";
import GroupItem from "../item/GroupItem";

const GroupTable = observer(() => {
    const {form} = useContext(Context)
    return (
    <div className="groups-row">
        {form.forms.length === 0 ?
            <p>Empty created groups</p>
            :
            form.forms.map(formItem =>
                <GroupItem key={formItem.id} groupEntity={formItem}/>
            )
        }
    </div>
    );
});

export default GroupTable;