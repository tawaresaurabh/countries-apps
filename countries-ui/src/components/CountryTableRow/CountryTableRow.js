import React from "react";
import { Button } from "react-bootstrap";




export default function CountryTableRow({ countryName, countryCode, onCountryLinkClick }) {



    const handleClick = (event) => {
        onCountryLinkClick(event.target.value)
    }


    return (

        <tr>
            <td>
                <Button variant="link" value={countryName} onClick={handleClick}>{countryName}</Button></td>
            <td>{countryCode}</td>
        </tr>


    )



}


