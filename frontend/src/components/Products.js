import styled from "styled-components";
import Product from "./Product";
import {useInfiniteQuery} from "react-query";
import {useCallback, useEffect, useRef} from "react";

const ProductsContainer = styled.div`
  padding-bottom: 75px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
`;

const ITEMS_PER_PAGE = 4;

const fetchProducts = async ({pageParam = 0}) => {
    const response = await fetch(`/products?cursor=${pageParam}&pageSize=${ITEMS_PER_PAGE}`);
    const data = await response.json();
    return { data: data.products, nextCursor: data.nextCursor};
}

const Products = () => {

    const {
        data, // 불러온 데이터
        fetchNextPage, // 다음 페이지 불러 오는 함수
        hasNextPage, // 다음 페이지 존재 여부 확인
        isFetchingNextPage,
    } = useInfiniteQuery('products', fetchProducts, {
        getNextPageParam: (lastPage) => lastPage.nextCursor ?? false, // 다음 페이지 파라미터 설정
    });

    const observerElem = useRef(null); // 관찰할 요소 참조
    const loadingRef = useRef(false); // useRef를 사용하는 이유 : 컴포넌트의 리렌더링 없이 사용하기 위해

    const handleObserver = useCallback((entries) => { // 의존성 배열에 포함된 값이 변경될 때마다 새로운 함수
        const [entry] = entries;

        if(entry.isIntersecting && hasNextPage && !loadingRef.current) {
            loadingRef.current = true; // 로딩인 상태를 true로 설정
            fetchNextPage().finally(() => {
               loadingRef.current = false; // 로딩이 끝나는 경우 false로 설정
            });
        }
    },[fetchNextPage, hasNextPage, loadingRef.current]);

    useEffect(() => {
        const observer = new IntersectionObserver(handleObserver, {
            root:null,
            rootMargin: '0px',// 관찰 대상이 뷰포트 경계에 도달할 때 콜백 실행
            threshold: 1.0,// 관찰 대상이 100% 뷰포트에 보일 때 콜백 실행
        });

        const currentElem = observerElem.current;

        if (currentElem) observer.observe(currentElem);


        return () => { // 클린업으로 함수 관찰 멈추기
            if (currentElem) observer.unobserve(currentElem);
        }
    }, [handleObserver,observerElem.current]);

    return (
        <ProductsContainer>
            {data?.pages.map((page, pageIndex) => (
                page.data.map((product, productIndex) => {
                    const isLast = pageIndex === data.pages.length - 1 && productIndex === page.data.length - 1;
                    return (
                        <Product
                            key={`${pageIndex}-${productIndex}`}
                            src={product.imageUrl}
                            name={product.name}
                            price={product.price.toLocaleString()}
                            stock={product.stock.toLocaleString()}
                            ref={isLast ? observerElem : null}
                        />
                    );
                })
            ))}
        </ProductsContainer>
    );
}

export default Products;