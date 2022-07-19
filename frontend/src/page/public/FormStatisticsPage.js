/* eslint-disable */
import React, {useContext, useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import PricingComponent from "../../component/PricingComponent";
import {fetchFormQuestions, fetchFormStatistics, fetchManagedForms} from "../../http/formAPI";
import {Context} from "../../index";
import {useParams} from "react-router-dom";
import FormItem from "../../component/item/FormItem";

const FormStatisticsPage = observer(() => {
    const {user} = useContext(Context)
    let { id } = useParams();

    const [formName, setFormName] = useState('');
    const [description, setDescription] = useState('');
    const [viewsCount, setViewsCount] = useState(0);
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        fetchFormStatistics(id, user.id).then(data => {
            setQuestions(data.questionEntities)
            data.questionEntities.map((question, questionId)=> {
                var possibleColors = ["red", "green","blue","orange","brown"]
                var xValues = [];
                var yValues = [];
                var barColors = [];
                question.variantEntityList.map((variant, variantId) => {
                    xValues[variantId] = variant.name
                    yValues[variantId] = variant.clickNumbers
                    barColors[variantId] = possibleColors[variantId]
                })
                console.log(xValues, yValues, barColors)
                new Chart("question" + questionId, {
                    type: "pie",
                    data: {
                        labels: xValues,
                        datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                    },
                    options: {
                        title: {
                            display: true,
                            text: question.name
                        }
                    }
                });
            })
            setViewsCount(data.viewsCount)
            setFormName(data.formName)
            setDescription(data.description)
        })
    }, [])



   console.log(questions)
    return (
        <div className="form_page">
            <div className="wrapper">
                <div className="row statistics_title">
                    <div className="col">
                    </div>
                    <div className="col-md-10">
                        <h1>Form {formName} statistics</h1>
                    </div>
                    <div className="col"></div>
                </div>
                <h1>Title: {formName}</h1>
                <h3>Description: {description}</h3>
                <h4>Views count: {viewsCount}</h4>
                <br/><br/>
                <div className="statistics">
                    <div className="row">
                        {questions.length === 0 ?
                            <p>Empty statistics</p>
                            :
                            questions.map((question, questionId) =>
                                <div className="statistics_item">
                                    <h4>Question: {question.name}</h4>
                                    <div className="row">
                                        <div className="col"></div>
                                        <div className="col-md-5">
                                            <canvas id={"question"+questionId} className="chart"></canvas>
                                        </div>
                                        <div className="col"></div>
                                    </div>
                                </div>
                            )
                        }
                    </div>
                </div>
            </div>
        </div>
    );
});

export default FormStatisticsPage;