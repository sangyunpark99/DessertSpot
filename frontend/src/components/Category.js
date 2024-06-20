import styled from "styled-components";

const CategoryContainer = styled.div`
  width: 22%;
  text-align: center;
  margin-bottom: 1rem;
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const CategoryOutlineImage = styled.div`
  height: 80px;
  width: 80px;
  padding-bottom: 1px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 50%;
  object-fit: scale-down;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
`

const CategoryImage = styled.img`
  width: 86%;
  height: 86%;
  border-radius: 50%;
  object-fit: scale-down;
  background: #f0f0f0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
`

const CategoryLabel = styled.div`
  margin-top: 0.5rem;
  font-size: 0.8rem;
  font-weight: 600;
`

const Category = ({src, label}) => {
    return(
        <CategoryContainer>
            <CategoryOutlineImage>
                <CategoryImage src={src}/>
            </CategoryOutlineImage>
            <CategoryLabel>{label}</CategoryLabel>
        </CategoryContainer>
    );
}

export default Category;