import React from "react";
import Modal from 'react-bootstrap/Modal'
import { Button, Table } from "react-bootstrap";

export default function CountriesDetail({ showModal, onOKHanlder, country }) {

    return (

        <Modal show={showModal} aria-labelledby="contained-modal-title-vcenter" centered>
            <Modal.Header className="confirmation-dialog-header-container">
                <Modal.Title>Country Details</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Table striped>


                    <tbody>

                        <tr>
                            <td>Name</td>
                            <td>{country.name}</td>
                        </tr>

                        <tr>
                            <td>Capital</td>
                            <td>{country.capital ? country.capital : 'INFORMATION NOT AVAILABLE'}</td>
                        </tr>

                        <tr>
                            <td>Population</td>
                            <td>{country.population ? country.population : 'INFORMATION NOT AVAILABLE'}</td>
                        </tr>

                        <tr>
                            <td>Country Code</td>
                            <td>{country.country_code ? country.country_code : 'INFORMATION NOT AVAILABLE'}</td>
                        </tr>


                        <tr>
                            <td>Flag url</td>
                            <td>
                                {country.flag_file_url ? <a href={country.flag_file_url}>{country.name}</a> : 'INFORMATION NOT AVAILABLE'}
                            </td>
                        </tr>


                    </tbody>



                </Table>


            </Modal.Body>

            <Modal.Footer >
                <Button onClick={onOKHanlder}> OK </Button>
            </Modal.Footer>


        </Modal>

    )



}


