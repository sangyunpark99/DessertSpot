import styled from "styled-components";

const BannerImage = styled.div`
  width: 100%;
  height: 28vh;
  background-color: #ffb0ed;
    //background-image: url(${props => props?.src});
  //background-size: cover; /* 이미지를 요소에 꽉 차게 */
  //background-position: center; /* 이미지를 가운데 위치하게 */
`;


const BannerItem = ({imageSrc}) => {
    return (
        <BannerImage src={imageSrc}/>
    );
}

export default BannerItem;