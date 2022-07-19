/* eslint-disable */

import React from 'react';

const GroupItem = ({groupEntity}) => {
    console.log(groupEntity)
    function handlePopup(){

    }
    return (
        <div className="group-item">
            <p>{groupEntity.name}</p>
            <button onClick={handlePopup}>Edit members</button>
            <button>Delete group</button>
        </div>
    );
};

export default GroupItem;