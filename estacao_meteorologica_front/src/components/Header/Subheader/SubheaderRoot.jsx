import PropTypes from 'prop-types';

const SubheaderRoot = ({ children }) => {
  return <div className="flex flex-row max-lg:flex-col place-content-between gap-8 max-lg:gap-3 my-6">{children}</div>;
};

SubheaderRoot.propTypes = {
  children: PropTypes.node.isRequired
};

export default SubheaderRoot;
