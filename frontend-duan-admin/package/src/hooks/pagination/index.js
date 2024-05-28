import React from 'react';
import Pagination from 'react-bootstrap/Pagination';
import "../../assets/css/pagination.css"

export default function UsePagination(props) {
    const { number, totalPages, setPage } = props

    let pagesToShow = []
    // Tính toán các trang cần hiển thị
    number > 1 && number < totalPages ? pagesToShow = [number] : pagesToShow = []
    let left = number - 1;
    let right = number + 1;
    let count = 3;

    // Hiển thị tối đa 5 trang
    while (totalPages >= 7 && pagesToShow.length < 5) {
        if (left > 1) {
            pagesToShow.unshift(left);
            left--;
        }
        if (right < totalPages) {
            pagesToShow.push(right);
            right++;
        }
    }

    while (totalPages >= 3 && totalPages < 7 && count <= totalPages) {
        if (left > 1) {
            pagesToShow.unshift(left);
            left--;
        }
        if (right < totalPages) {
            pagesToShow.push(right);
            right++;
        }
        count++;
    }

    const handleClick = (page) => {
        if (page === number - 1) return;
        if (page < 0 || page >= totalPages) return;
        setPage(page)
    }



    return (
        <>
            {totalPages >= 2 &&
                <>
                    <Pagination>
                        <div className='szie-page'>
                            <span>Number of page</span>
                            <div className='box-size'>
                                <span>5</span>
                                <svg className='icon-page-up' xmlns="http://www.w3.org/2000/svg" height="20px" viewBox="0 -960 960 960" width="20px" fill="#666666"><path d="M480-525 291-336l-51-51 240-240 240 240-51 51-189-189Z" /></svg>
                                <svg className='icon-page-dow' xmlns="http://www.w3.org/2000/svg" height="20px" viewBox="0 -960 960 960" width="20px" fill="#666666"><path d="M480-333 240-573l51-51 189 189 189-189 51 51-240 240Z" /></svg>
                            </div>
                        </div>
                        {/* <Pagination.First onClick={() => handleClick(0)} /> */}
                        <Pagination.Prev onClick={() => handleClick(number - 2)} />
                        <Pagination.Item onClick={() => handleClick(0)} active={number === 1}>{1}</Pagination.Item>
                        {totalPages > 7 && number > 4 && <Pagination.Ellipsis />}
                        {pagesToShow.map(page => (
                            <Pagination.Item key={page} onClick={() => { handleClick(page - 1) }} active={page === number}>{page}</Pagination.Item>
                        ))}
                        {totalPages > 7 && number < totalPages - 3 && <Pagination.Ellipsis />}
                        <Pagination.Item onClick={() => handleClick(totalPages - 1)} active={number === totalPages}>{totalPages}</Pagination.Item>
                        <Pagination.Next onClick={() => handleClick(number)} />
                        {/* <Pagination.Last onClick={() => handleClick(totalPages - 1)} /> */}
                    </Pagination>
                </>
            }
        </>
    );
}
