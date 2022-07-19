import React from 'react';
import {Routes, Route, Navigate} from 'react-router-dom';
import {observer} from "mobx-react-lite";
import RegistrationPage from "../page/public/RegistrationPage";
import LoginPage from "../page/public/LoginPage";
import PricingPage from "../page/public/PricingPage";
import PublicFormsPage from "../page/public/PublicFormsPage";
import MainPage from "../page/public/MainPage";
import FormPage from "../page/public/FormPage";
import FormCreation from "../page/public/FormCreation";
import GroupCreation from "../page/public/GroupCreation";
import ProfilePage from "../page/public/ProfilePage";
import NotificationsPage from "../page/public/NotificationsPage";
import ManagedFormsPage from "../page/public/ManagedFormsPage";
import FormStatisticsPage from "../page/public/FormStatisticsPage";
import AssignedGroupsPage from "../page/public/AssignedGroupsPage";
import ManagedGroupsPage from "../page/public/ManagedGroupsPage";
import PrivateFormsPage from "../page/public/PrivateFormsPage";

const AppRouter = observer(() => {
    return (
        <Routes>

            <Route path="/" element={<MainPage />} />
            <Route path="/form/:id" element={<FormPage />} />
            <Route path="/registration" element={<RegistrationPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/pricing" element={<PricingPage />} />
            <Route path="/public/forms" element={<PublicFormsPage />} />
            <Route path="/form/:id/statistics" element={<FormStatisticsPage />} />
            <Route path="/form-creation" element={<FormCreation />} />
            <Route path="/group-creation" element={<GroupCreation />} />
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/assigned/groups" element={<AssignedGroupsPage />} />
            <Route path="/managed/groups" element={<ManagedGroupsPage />} />
            <Route path="/notifications" element={<NotificationsPage />} />
            <Route path="/private/forms" element={<PrivateFormsPage />} />
            <Route path="/managed/forms" element={<ManagedFormsPage />} />
            <Route path="*" element={<Navigate to ="/" />}/>
        </Routes>

    );
});




export default AppRouter;