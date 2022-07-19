/* eslint-disable */
import React, {useContext, useState} from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";
import {login} from "../../http/userAPI";
import {Context} from "../../index";
import {createForm} from "../../http/formAPI";

const FormCreation = observer(() => {

    const {user} = useContext(Context)

    const [tab, setTab] = useState('formCreation');
    const [title, setTitle] = useState('Create form');
    const [formType, setFormType] = useState('PUBLIC');
    const [formName,setFormName] = useState('');
    const [description,setDescription] = useState('');
    const [questions, setQuestion] = useState([

    ]);


    const handleAddQuestion = () => {
        setQuestion([...questions,
            // now you can add a new object to add to the array
            {
                name: "",
                questionType: "RADIOBUTTON",
                variantEntityList: [
                    {
                        name: "Variant 1"
                    }
                ]
            }])
    }

    function handleAddVariant(index) {
        console.log(index)
        const values = [...questions];
        let variants = [...values[index]["variantEntityList"], {name : ""}]
        values[index]["variantEntityList"] = variants
        setQuestion(values)
        console.log(values)
    }

    const handleSubmit = async (e) => {
        try {
            let data;
            data = await createForm(formName, description, user.id, formType, questions);
            console.log(data)
            alert("Form created!")
            window.location.href = '/managed/forms'
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    const handleChangeInput = (index, event) => {
        console.log(index, event.target.name)
        const values = [...questions];
        values[index][event.target.name] = event.target.value;
        setQuestion(values);
    }

    const handleVariantInput = (index, variantId, event) => {
        console.log(index, variantId, event.target.name)
        const values = [...questions];
        values[index]["variantEntityList"][variantId][event.target.name] = event.target.value;
        setQuestion(values);
    }

    function handleChangeTab(formSettings, title) {
        setTab(formSettings);
        setTitle(title);
    }

    return (
        <div className="form_page">
            <div className="wrapper">
                <h1>{title}</h1>
                {(() => {
                    if (tab === "formCreation") {
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
                                                        <p>Form name</p>
                                                    </div>
                                                    <div className="col-md-8">
                                                        <input type="text" placeholder="Form name" name="name" value={formName} onChange={event => setFormName(event.target.value)}/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-description">
                                                <div className="row">

                                                    <div className="col-md-4">
                                                        <p>Description</p>
                                                    </div>
                                                    <div className="col-md-8">
                                                        <textarea placeholder="Description" name="description" value={description} onChange={event => setDescription(event.target.value)}></textarea>
                                                    </div>

                                                </div>
                                            </div>
                                            <div className="form-questions">
                                                {questions.map((question, index) => (
                                                    <div className="form-question">
                                                        <div className="row">
                                                            <div className="col-md-4">
                                                                <p>Question name</p>
                                                            </div>
                                                            <div className="col-md-8">
                                                                <input name="name" type="text" value={question.name} onChange={event => handleChangeInput(index, event)}/>
                                                            </div>
                                                        </div>
                                                        <div className="row">
                                                            <div className="col-md-4">
                                                                <p>Question type</p>
                                                            </div>
                                                            <div className="col-md-8">
                                                                <select name="questionType" onChange={event => handleChangeInput(index, event)} value={question.questionType}>
                                                                    <option value="RADIOBUTTON">radio</option>
                                                                    <option value="CHECKBOX">checkbox</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div className="variants-block">
                                                            <div className="variants">
                                                                {question.variantEntityList.map((variant, variantId) => (
                                                                    <div className="variant">
                                                                        {question.questionType === "CHECKBOX" ?
                                                                            <div className="row">
                                                                                <div className="col-md-1">
                                                                                    <i className="fa-solid fa-square-check"></i>
                                                                                </div>
                                                                                <div className="col">
                                                                                    <input type="text" name="name" value={variant.name} onChange={event => handleVariantInput(index, variantId, event)}/>
                                                                                </div>
                                                                            </div>
                                                                            :
                                                                            <div className="row">
                                                                                <div className="col-md-1">
                                                                                    <i className="fa-solid fa-circle-dot"></i>
                                                                                </div>
                                                                                <div className="col">
                                                                                    <input type="text" name="name" value={variant.name} onChange={event => handleVariantInput(index, variantId, event)}/>
                                                                                </div>
                                                                            </div>
                                                                        }
                                                                    </div>
                                                                ))}

                                                                <button onClick={() => handleAddVariant(index)}>Add variant</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                ))}
                                            </div>
                                            <div className="questions_btn">
                                                <div className="row">
                                                    <div className="col-md-3">
                                                        <button onClick={handleAddQuestion}>Add question</button>
                                                    </div>
                                                    <div className="col"></div>
                                                    <div className="col-md-3">
                                                        <button onClick={() => handleChangeTab('formSettings', 'Form settings')}>Next</button>
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
                    } else if (tab === "formSettings") {
                        return (
                            <div>
                                <div className="form-creation">
                                    <div className="row">
                                        <div className="col">
                                        </div>
                                        <div className="col-md-8">
                                            <div className="form-name">
                                                <div className="row">
                                                    <div className="col-md-8">
                                                        <p>Form type</p>
                                                    </div>
                                                    <div className="form-check">
                                                        <input className="form-check-input" type="radio"
                                                               name="formType" id="formType1" value="PUBLIC"
                                                               checked={formType === "PUBLIC"} onChange={(e)=>{setFormType(e.target.value)}} />
                                                        <label className="form-check-label"
                                                               htmlFor="formType1">
                                                            Public form
                                                        </label>
                                                    </div>
                                                    <div className="form-check">
                                                        <input className="form-check-input" type="radio"
                                                               name="formType" id="formType2" value="PRIVATE"
                                                               checked={formType === "PRIVATE"} onChange={(e)=>{setFormType(e.target.value)}} />
                                                        <label className="form-check-label"
                                                               htmlFor="formType2">
                                                            Private form
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="questions_btn">
                                                <div className="row">
                                                    <div className="col-md-3">
                                                        <button onClick={() => handleChangeTab('formCreation', 'Create form')}>Back</button>
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

export default FormCreation;