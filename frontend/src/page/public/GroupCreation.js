/* eslint-disable */
import React, {useContext, useState} from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";
import {createForm} from "../../http/formAPI";
import {Context} from "../../index";
import {createGroup} from "../../http/groupAPI";

const GroupCreation = observer(() => {

    const {user} = useContext(Context)

    const [tab, setTab] = useState('groupCreation');
    const [title, setTitle] = useState('Create new group');
    const [groupName,setGroupName] = useState('');
    const [groupDescription,setGroupDescription] = useState('');
    const [users,setUsers] = useState([]);


    const handleSubmit = async () => {
        try {
            let data;
            data = await createGroup(groupName, user.id, users);
            console.log(data)
            alert("Group created!")
            window.location.href = '/managed/groups'
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    return (
        <div className="group_page">
            <div className="wrapper">
                <h1>{title}</h1>
                {(() => {
                    if (tab === "groupCreation") {
                        return (
                            <div>
                                <div className="form-creation">
                                    <div className="row">
                                        <div className="col">
                                        </div>
                                        <div className="col-md-8">
                                            <div className="form-name">
                                                <div className="row">
                                                    <div className="col-md-4">
                                                        <p>Group name</p>
                                                    </div>
                                                    <div className="col-md-8">
                                                        <input type="text" placeholder="Group name" name="name" value={groupName} onChange={event => setGroupName(event.target.value)}/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="questions_btn">
                                                <div className="row">

                                                    <div className="col"></div>
                                                    <div className="col-md-3">
                                                        <button onClick={handleSubmit}>Create</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col">
                                        </div>

                                    </div>
                                </div>
                            </div>
                        )
                    } else if (tab === "membersSettings") {
                        return (
                            <div>
                                <div className="form-creation">
                                    <div className="row">
                                        <div className="col">
                                        </div>
                                        <div className="col-md-8">
                                            <div className="form_users card">
                                                <div className="card-header">
                                                    Actual members:
                                                </div>
                                                <ul className="list-group list-group-flush">
                                                    {groupMembers.map((member) => (
                                                        <li className="list-group-item">{member.name} {member.surname} <button onClick={() => handleDelete(member.id)}><i className="fa-solid fa-xmark"></i></button></li>

                                                    ))}
                                                </ul>
                                            </div>

                                            <div className="form-name">
                                                <div className="row">
                                                    <div className="col-md-4">
                                                        <p>Find user: </p>
                                                    </div>
                                                    <div className="col-md-8">
                                                        <input type="text" placeholder="Email" name="name"/>
                                                    </div>
                                                </div>
                                                <div className="row search_results">
                                                    <div className="col-md-12">
                                                        gf
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="questions_btn">
                                                <div className="row">
                                                    <div className="col-md-3">
                                                        <button onClick={() => handleChangeTab('groupCreation', 'Group creation')}>Back</button>
                                                    </div>
                                                    <div className="col"></div>
                                                    <div className="col-md-3">
                                                        <button onClick={handleSubmit}>Submit form</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="col">
                                        </div>

                                    </div>
                                </div>
                            </div>
                        )
                    }
                })()}
            </div>
        </div>
    );
});

export default GroupCreation;