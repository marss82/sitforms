/* eslint-disable */
import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {Context} from "../../index";
import FormTable from "../../component/table/FormTable";
import {fetchManagedForms} from "../../http/formAPI";
import {fetchAssignedGroups, fetchManagedGroups} from "../../http/groupAPI";
import GroupTable from "../../component/table/GroupTable";
import GroupItem from "../../component/item/GroupItem";
import {addMemberById, findUserByEmail, login} from "../../http/userAPI";

const ManagedGroupsPage = observer(() => {
    const {form} = useContext(Context)
    const {user} = useContext(Context)

    const [users, setUsers] = useState([]);
    const [formItem,setFormItem] = useState(null);
    const [userId, setUserId] = useState(null)
    const [email,setEmail] = useState('');

    function handlePopup(users, formItem){
        console.log(users)
        var element = document.getElementById("popup");
        element.classList.remove("hiden")
        setUsers(users)
        setFormItem(formItem)
    }

    function handleRemovePopup(){
        var element = document.getElementById("popup");
        element.classList.add("hiden")
    }

    const addMember = async () => {
        try {
            let data;
            data = await findUserByEmail(email);
            setUserId(data.id)
            console.log(data.id, formItem.id)
            await addMemberById(data.id, formItem.id)
            let users;
            users = await fetchGroupUsers(formItem.id);
            setUsers(users)
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    function deleteMember(userId){
        alert(userId)
    }

    useEffect(() => {
        fetchManagedGroups(user.id).then(data => {
            form.setForms(data)
        })
    }, [])
    return (
        <div className="forms-container">
            <div id="popup" className="popup hiden">
                <div className="popup-form">
                    <div className="close-popup">
                        <img onClick={handleRemovePopup} src="https://d29fhpw069ctt2.cloudfront.net/icon/image/39219/preview.png" alt=""/>
                    </div>

                    <div className="group-users">
                        <div className="form_users card">
                            <div className="card-header">
                                Actual users
                            </div>
                            <ul className="list-group list-group-flush">
                                {users.map(user => (
                                    <li className="list-group-item">{user.name} {user.surname}<button onClick={() => deleteMember(user.id)}><i className="fa-solid fa-xmark"></i></button></li>
                                ))}
                            </ul>
                        </div>

                        <div className="form-name">
                            <div className="row">
                                <div className="col-md-4">
                                    <p>Add user: </p>
                                </div>
                                <div className="col-md-6">
                                    <input type="text" placeholder="Email" name="name" value={email} onChange={ e => setEmail(e.target.value)}/>
                                </div>
                                <div className="col-md-2">
                                    <button onClick={addMember}>Add member</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="forms">
                <div className="wrapper">
                    <h1>Created groups</h1>
                    <div className="groups-row">
                        {form.forms.length === 0 ?
                            <p>Empty created groups</p>
                            :
                            form.forms.map(formItem =>
                                <div className="group-item">
                                    <p>{formItem.name}</p>
                                    <button onClick={() => handlePopup(formItem.userEntityList, formItem)}>Edit members</button>
                                    <button>Delete group</button>
                                </div>
                            )
                        }
                    </div>
                </div>
            </div>
        </div>
    );
});

export default ManagedGroupsPage;