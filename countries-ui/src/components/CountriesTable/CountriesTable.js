import React, { useEffect, useState } from "react";
import { Container, Table } from "react-bootstrap";
import axios from 'axios';
import CountryTableRow from '../CountryTableRow/CountryTableRow'
import CountriesDetail from '../CountriesDetail/CountriesDetail'
import Alert from 'react-bootstrap/Alert'

export default function CountriesTable(props) {

    const [countryList, setCountryList] = useState([])
    const [clickCountry, setClickedCountry] = useState('')
    const [showModal, setShowModal] = useState(false)
    const [country, setCountry] = useState({})
    const [isServerError, setServerError] = useState(false)




    useEffect(() => {
        setUrl()
        axios.get('/')
            .then(function (response) {
                setServerError(false)
                console.log(response.data.countries);
                setCountryList(response.data.countries)
            })
            .catch(function (error) {
                setServerError(true)
                console.log(error);
            })
    }, []);


    useEffect(() => {

        console.log(clickCountry)
        if (clickCountry) {
            setUrl()
            axios.get(`/${clickCountry}`)
                .then(function (response) {
                    setServerError(false)
                    setCountry(response.data)
                    setShowModal(true)
                })
                .catch(function (error) {
                    console.log(error);
                })
        }

    }, [clickCountry]);



    const setUrl = () => {
        axios.defaults.baseURL = `http://localhost:8080/countries`;
    }


    const handleCountryLinkClick = (value) => {
        setClickedCountry(value)

    }


    const handleModalOK = () => {
        setShowModal(false)
        setCountry({})
    }


    return (



        <Container>

            {
                isServerError &&
                <Alert variant="danger">Error while fetching data</Alert>
            }


            {

                !isServerError &&

                <Table striped>
                    <thead>
                        <tr>
                            <th>Country Name</th>
                            <th>Country Code</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            countryList.length > 0 &&
                            countryList.map((country, index) => (<CountryTableRow key={index} countryName={country.name} countryCode={country.country_code} onCountryLinkClick={handleCountryLinkClick} />))

                        }
                    </tbody>
                </Table>





            }

            {showModal && !isServerError &&
                <CountriesDetail country={country} showModal={showModal} onOKHanlder={handleModalOK} />
            }

        </Container>





    )


}


