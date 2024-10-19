import PropTypes from 'prop-types';

const SubheaderItem = ({ iconSrc, textContent }) => {
  return (
    <div className="border-2 border-black rounded-2xl flex p-4 justify-center w-full max-lg:justify-start">
      <img
        src={iconSrc}
        className="w-[35px] mr-3"
      />
      <b className="self-center">{textContent}</b>
    </div>
  );
};

SubheaderItem.propTypes = {
  iconSrc: PropTypes.string.isRequired,
  textContent: PropTypes.node.isRequired
};

export default SubheaderItem;
