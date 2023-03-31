package com.bankx.application.service.mapper;


import com.bankx.application.domain.Branch;
import com.bankx.application.service.dto.BranchDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BranchMapper implements EntityMapper<BranchDTO, Branch> {

    @Override
    public Branch toEntity(BranchDTO dto) {
        Branch branch = new Branch();
        branch.setId(dto.getId());
        branch.setAddress(dto.getAddress());
        branch.setIfscCode(dto.getIfscCode());
        branch.setName(dto.getName());
        branch.setBank(dto.getBank());
        return branch;
    }

    @Override
    public BranchDTO toDto(Branch branch) {
        return new BranchDTO(branch);
    }

    @Override
    public List<Branch> toEntity(List<BranchDTO> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<BranchDTO> toDto(List<Branch> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }


}
