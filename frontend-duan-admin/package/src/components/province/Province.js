import React, { useCallback, useEffect, useState } from 'react';
import { Card, CardTitle, Table, FormGroup, Label, Input } from "reactstrap";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from 'axios';

const Province = () => {
    const [province, setProvince] = useState({ districts: [] })
    const [checkeds, setCheckeds] = useState([])
    const [page, setPage] = useState(0)
    const [size, setSize] = useState(5)
    const [isSort, setIsSort] = useState(false)
    const [modalShow, setModalShow] = useState(false)

    // call api

    const [provinces, setProvinces] = useState()
    const getApiProvince = useCallback(async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/v1/province/getPage?page=${page}&&size=${size}`);
            const data = res.data ? res.data : []
            setProvinces(data)
        } catch (e) {

        }
    }, [page, size])

    useEffect(() => {
        getApiProvince();
    }, [getApiProvince])

    const createProvince = async () => {
        await axios.post(`http://localhost:8080/api/v1/province/create`, province)
    }
    // End Call api

    // xu ly check
    const handleCheck = (id) => {
        let copy = [...checkeds]
        const index = copy.indexOf(id)
        if (index === -1) {
            copy.push(id)
            setCheckeds((prev) => [...prev, id])
        } else {
            copy.splice(index, 1)
            setCheckeds([...copy])
        }
    }

    const handleCheckAll = (checked) => {
        setCheckeds([])
        if (checked) {
            provinces.content.map(item => (
                setCheckeds((prev) => [...prev, item.id])
            ))
        }
    }

    // End checked

    // sap xep data
    const sortData = (id) => {
        if (!id) return
        setIsSort(!isSort)
        if (!isSort) {
            sortDataGraduallySmaller(id)
        } else {
            sortDataAscending(id)
        }
    }

    const sortDataAscending = (data) => {
        let newData = [...provinces.content]
        if (!newData?.map(p => p[data])) return
        newData?.sort((a, b) => a[data] > b[data] ? 1 : -1)
        setProvinces((prev) => ({ ...prev, content: newData }))
    }

    const sortDataGraduallySmaller = (data) => {
        let newData = [...provinces.content]
        if (!newData?.map(p => p[data])) return
        newData?.sort((a, b) => a[data] > b[data] ? 1 : -1)
        setProvinces((prev) => ({ ...prev, content: newData }))
    }
    // End sort

    const [validated, setValidated] = useState(false);
    const handleCreate = () => {
        const form = document.getElementById('create-course-form').checkValidity();
        if (form === false) {
            // show điều khiện form
            setValidated(true);
            return
        }
        createProvince()
        getApiProvince()
    };

    const handleSelect = (value) => {
        const data = { ...province };
        data.districts = value
        setProvince(data)
    }

    const closeModal = () => {
        setModalShow(false);
        setProvince({ districts: [] })
    }
    return (
        <div>
            <Card>
                <CardTitle tag="h6" className='border-bottom p-3 mb- 0 province-cart-header'>
                    <Form className="d-flex search-data">
                        <Form.Control
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                        />
                        <Button variant="outline-success">Search</Button>
                    </Form>
                    <div className="box-action">
                        {checkeds.length > 0 &&
                            <div className="box-delete">
                                <i className="bi bi-archive"></i>
                                Xóa
                            </div>
                        }
                        <div className="box-add" onClick={() => setModalShow(true)}>
                            <i className="bi bi-plus-circle"></i>
                            Thêm
                        </div>
                    </div>
                </CardTitle>
            </Card>
        </div>
    );
};

export default Province;