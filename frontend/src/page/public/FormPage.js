/* eslint-disable */
import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";
import {createForm, fetchFormQuestions, fetchManagedForms, increaseViewsCount, sendAnswers} from "../../http/formAPI";
import {Context} from "../../index";
import {useParams} from "react-router-dom";
import FormItem from "../../component/item/FormItem";

const FormPage = observer(() => {
    const {user} = useContext(Context)
    const {question} = useContext(Context)
    let { id } = useParams();
    const [isLoading, setisLoading] = useState(true);
    const [formName, setFormName] = useState('');
    const [formId, setFormId] = useState(null);
    const [description, setDescription] = useState('');
    const [questions, setQuestions] = useState([

    ]);
    const answers = []
    const usedVariantsId = []
    const usedQuestions = []

    useEffect(() => {
        increaseViewsCount(id).then(r => console.log(r))

        fetchFormQuestions(id).then(data => {
            setFormName(data.formName)
            setQuestions(data.questionEntities)
            setDescription(data.description)
            setFormId(data.id)
            setisLoading(false)
        })
    }, [])

    function handleAddAnswer(questionId, chosenVariantId, el){
        let type = el.type
        console.log(questionId, chosenVariantId)
        if (!usedQuestions.includes(questionId)){
            console.log("adding new question")
            usedQuestions.push(questionId)
            usedVariantsId.push(chosenVariantId)
            answers.push({
                chosenQuestionId: questionId,
                chosenVariants: [
                    {
                        chosenVariantId: chosenVariantId
                    }
                ]
            })
        } else if (usedQuestions.includes(questionId)) {
            if (usedVariantsId.includes(chosenVariantId)){
                if(type === "checkbox") {
                    // if variant has been picked
                    console.log("deleting variant")
                    let answerIndex = answers.findIndex(item => item.chosenQuestionId === questionId);
                    let questionIndex = answers[answerIndex].chosenVariants.findIndex(item => item.chosenVariantId === chosenVariantId);
                    let usedVariantIndex = usedVariantsId.findIndex(item => item === chosenVariantId);
                    usedVariantsId.splice(usedVariantIndex, 1)
                    console.log(usedVariantsId)
                    answers[answerIndex].chosenVariants.splice(questionIndex, 1)
                }
            } else if (!usedVariantsId.includes(chosenVariantId)){
                console.log("adding variant")
                let answerIndex = answers.findIndex(item => item.chosenQuestionId === questionId);
                if (type === "checkbox") {
                    answers[answerIndex].chosenVariants.push({
                        chosenVariantId: chosenVariantId
                    })
                } else {
                    answers[answerIndex].chosenVariants = []
                    answers[answerIndex].chosenVariants.push({
                        chosenVariantId: chosenVariantId
                    })
                }
                usedVariantsId.push(chosenVariantId)
            }
        }
    }


    const submitAnswers = async () => {
        try {
            let data;
            data = await sendAnswers(formId, answers);
            alert("Form submit")
            window.location.href = '/public/forms'
            console.log(data)
        } catch (e) {
            alert(e.response.data.message)
        }
    }

    return (
        <div className="form_page">
            <div className="wrapper">
                <h1>Form name: {formName}</h1>
                <h3>Form description: {description}</h3>
                <br/><br/>
                <div className="questions">
                    {isLoading? "" :
                        questions.length === 0 ?
                            <p>Empty questions</p>
                            :
                            questions.map((question, questionId) =>
                                <div className="question_item">
                                    <div className="question_item_head">
                                        <p>{question.name}</p>
                                    </div>
                                    <div className="question_variants">
                                        {question.variantEntityList.map((variant, variantId) =>
                                            <div className="question_variant">
                                                <input type={question.questionType === 'RADIOBUTTON' ? 'radio' : 'checkbox'} onClick={event => handleAddAnswer(question.id, variant.id, event.target)} className="btn-check" name="options" id={questionId + '-' + variantId}
                                                       autoComplete="off"/>
                                                <label className="btn btn-secondary" htmlFor={questionId + '-' + variantId}>
                                                    {question.questionType === 'RADIOBUTTON' ?
                                                        <i className="fa-solid fa-circle-dot"></i>
                                                        : <i className="fa-solid fa-square-check"></i>} {variant.name}</label>
                                            </div>
                                        )}
                                    </div>
                                </div>
                            )
                    }

                </div>

                <div className="questions_btn">
                    <div className="row">
                        <div className="col"></div>
                        <div className="col-md-3">
                            <button onClick={submitAnswers}>Submit</button>
                        </div>
                        <div className="col"></div>
                    </div>
                </div>
            </div>
        </div>
    );
});

export default FormPage;